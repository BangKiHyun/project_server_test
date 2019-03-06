package com.bang.dbtest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
        private int userIdx;
        private String name;
        private String gender;
        private int age;
        private String status;
        private String voicefileUrl1;
        private String voicefileUrl2;
        private String voicefileUrl3;
        private String videofileUrl;
}
