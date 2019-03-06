package com.bang.dbtest.mapper;

import com.bang.dbtest.dto.User;
import com.bang.dbtest.model.SignUpReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM alcohol.user")
    List<User> findAll();

    //Idx값 Auto Increment(함수명 int)
    @Insert("INSERT INTO alcohol.user (userIdx, name, gender, age) VALUES (#{user.userIdx} #{user.name}, #{user.gender}, #{user.age})")
    @Options(useGeneratedKeys = true, keyColumn = "user.userIdx")
    int saveUser(@Param("user") final User user);

    //User 삭제
    @Delete("DELETE FROM alcohol.user where userIdx = #{userIdx}")
    void deleteByUserIdx(@Param("userIdx") final int userIdx);

/*    //User VoiceFile 삭제
    @Delete("delete from alcohol.user where voicefileUrl = (select voicefileUrl from alcohol.user where userIdx = #{userIdx})")
    void deleteByVoiceFile(@Param("userIdx") final int userIdx);

    //User VideoFile 삭제
    @Delete("delete from alcohol.user where voicefileUrl = (select videofileUrl from alcohol.user where userIdx = #{userIdx})")
    void deleteByVideoFile(@Param("userIdx") final int userIdx);*/

    //수정
    @Update("UPDATE user SET alcohol.name = #{user.name}, gender = #{user.gender}, age = #{user.age} where userIdx = #{user.userIdx}")
    void update(@Param("userIdx") final int userIdx, @Param("user") final User user);

    //VoiceFile 삭제
    @Update("UPDATE alcohol.user SET voicefileUrl = NULL WHERE userIdx = #{userIdx}")
    void deleteByVoiceFile(@Param("userIdx") final int userIdx);

    //VideoFile 삭제
    @Update("UPDATE alcohol.user SET videofileUrl = null WHERE userIdx = #{userIdx}")
    void deleteByVideoFile(@Param("userIdx") final int userIdx);

    @Select("SELECT * FROM alcohol.user WHERE userIdx = #{userIdx}")
    User findByUserIdx(@Param("userIdx") final int userIdx);


    //최종 저장
    @Insert("INSERT INTO alcohol.user(name, gender, age, status, voicefileUrl1, voicefileUrl2, voicefileUrl3, videofileUrl) VALUES(#{signUpReq.name}, #{signUpReq.gender}, #{signUpReq.age}, #{signUpReq.status}, #{signUpReq.voicefileUrl1}, #{signUpReq.voicefileUrl2}, #{signUpReq.voicefileUrl3}, #{signUpReq.videofileUrl})")
    @Options(useGeneratedKeys = true, keyColumn = "alcohol.user.userIdx")
    int save(@Param("signUpReq") final SignUpReq signUpReq);
}
