package com.bang.dbtest.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUpReq {
    //User 정보
    private int userIdx;
    private String name;
    private String gender;
    private int age;
    private String status;

    //음성 파일
    private MultipartFile voicefile1;
    private String voicefileUrl1;
    private MultipartFile voicefile2;
    private String voicefileUrl2;
    private MultipartFile voicefile3;
    private String voicefileUrl3;

    //영상 파일
    private MultipartFile videofile;
    private String videofileUrl;
}
