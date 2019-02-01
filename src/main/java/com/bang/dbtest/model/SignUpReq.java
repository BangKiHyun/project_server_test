package com.bang.dbtest.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUpReq {
    //User 정보
    private String name;
    private String gender;
    private int age;
    private String status;

    //음성 파일
    private MultipartFile voicefile;
    private String voicefileUrl;

    //영상 파일
    private MultipartFile videofile;
    private String videofileUrl;
}
