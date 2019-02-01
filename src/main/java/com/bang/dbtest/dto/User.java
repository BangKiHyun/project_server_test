package com.bang.dbtest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
        private String userIdx;
        private String name;
        private String gender;
        private int age;
        private String voicefileUrl;
        private String videofileUrl;
}
