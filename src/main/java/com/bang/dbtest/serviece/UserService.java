package com.bang.dbtest.serviece;

import com.bang.dbtest.dto.User;
import com.bang.dbtest.mapper.UserMapper;
import com.bang.dbtest.model.DefaultRes;
import com.bang.dbtest.model.SignUpReq;
import com.bang.dbtest.utils.ResponseMessage;
import com.bang.dbtest.utils.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;


@Slf4j
@Service
public class UserService {

    private final UserMapper userMapper;
    private final S3FileUpload s3FileUpload;

    public UserService(final UserMapper userMapper, final S3FileUpload s3FileUpload) {
        this.userMapper = userMapper;
        this.s3FileUpload = s3FileUpload;
    }

/*    *//**
     * 모든 회원 조회
     *
     * @return DefaultRes
     *//*
    public DefaultRes getAllUsers() {
        final List<User> userList = userMapper.findAll();
        if (userList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, userList);
    }*/


//    /**
//     * 이름으로 회원 조회
//     *
//     * @param name 이름
//     * @return DefaultRes
//     */
//    public DefaultRes findByName(final String name) {
//        final User user = userMapper.findByName(name);
//        if (user == null)
//            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);
//        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, user);
//    }


    /**
     * 회원 가입
     *
     * @param user 회원 데이터
     * @return DefaultRes
     */
    @Transactional
    public DefaultRes saveUser(final User user) {
        try {
            userMapper.saveUser(user);
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER);
        } catch (Exception e) {
            //Rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }


    @Transactional
    public DefaultRes save(SignUpReq signUpReq) {
        try {
            //파일이 있다면 파일을 S3에 저장 후 경로를 저장
            if (signUpReq.getVoicefile1() != null)
                signUpReq.setVoicefileUrl1(s3FileUpload.upload(signUpReq.getVoicefile1()));
            if (signUpReq.getVoicefile2() != null)
                signUpReq.setVoicefileUrl2(s3FileUpload.upload(signUpReq.getVoicefile2()));
            if (signUpReq.getVoicefile3() != null)
                signUpReq.setVoicefileUrl3(s3FileUpload.upload(signUpReq.getVoicefile3()));
            if (signUpReq.getVideofile() != null)
                signUpReq.setVideofileUrl(s3FileUpload.upload(signUpReq.getVideofile()));

            userMapper.save(signUpReq);
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER);
        } catch (Exception e) {
            //Rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    /**
     * 회원 정보 수정
     *
     * @param userIdx 회원 고유 번호
     * @param user    수정할 회원 데이터
     * @return DefaultRes
     */
    @Transactional
    public DefaultRes update(final int userIdx, final User user) {
        User temp = userMapper.findByUserIdx(userIdx);
        if (temp == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);

        try {
            if (user.getName() != null) temp.setName(user.getName());
            if (user.getGender() != null) temp.setGender(user.getGender());
            userMapper.update(userIdx, temp);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.UPDATE_USER);
        } catch (Exception e) {
            //Rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    //User voicefileUrl


    /**
     * 회원 탈퇴
     *
     * @param userIdx 회원 고유 번호
     * @return DefaultRes
     */
    @Transactional
    public DefaultRes deleteByUserIdx(final int userIdx) {
        final User user = userMapper.findByUserIdx(userIdx);
        if (user == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);

        try {
            userMapper.deleteByUserIdx(userIdx);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.DELETE_USER);
        } catch (Exception e) {
            //Rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    // Delete VoiceFile
    @Transactional
    public DefaultRes deleteByVoiceFile(final int userIdx) {
        final User user = userMapper.findByUserIdx(userIdx);
        if (user == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);

        try {
            userMapper.deleteByVoiceFile(userIdx);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.DELETE_USER);
        } catch (Exception e) {
            //Rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    // Delete VideoFile
    @Transactional
    public DefaultRes deleteByVideoFile(final int userIdx) {
        final User user = userMapper.findByUserIdx(userIdx);
        if (user == null)
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);

        try {
            userMapper.deleteByVideoFile(userIdx);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.DELETE_USER);
        } catch (Exception e) {
            //Rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
