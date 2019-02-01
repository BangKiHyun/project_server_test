package com.bang.dbtest.mapper;

import com.bang.dbtest.dto.User;
import com.bang.dbtest.model.SignUpReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM alcohol.user")
    List<User> findAll();

    //저장
    @Insert("INSERT INTO alcohol.user (userIdx, name, gender, age) VALUES (#{user.userIdx} #{user.name}, #{user.gender}, #{user.age})")
    @Options(useGeneratedKeys = true, keyColumn = "user.name")
    int saveUser(@Param("user") final User user);

    //삭제
    @Delete("DELETE FROM alcohol.user where userIdx = #{userIdx}")
    void deleteByUserIdx(@Param("userIdx") final int userIdx);

    //수정
    @Update("UPDATE user SET alcohol.name = #{user.name}, gender = #{user.gender}, age = #{user.age} where userIdx = #{user.userIdx}")
    void update(@Param("userIdx") final int userIdx, @Param("user") final User user);

    @Select("SELECT * FROM alcohol.user WHERE userIdx = #{userIdx}")
    User findByUserIdx(@Param("userIdx") final int userIdx);


    //최종 저장
    @Insert("INSERT INTO alcohol.user(name, gender, age, status, voicefileUrl, videofileUrl) VALUES(#{signUpReq.name}, #{signUpReq.gender}, #{signUpReq.age}, #{signUpReq.status}, #{signUpReq.voicefileUrl}, #{signUpReq.videofileUrl})")
        //@Options(useGeneratedKeys = true, keyColumn = "userIdx")
    void save(@Param("signUpReq") final SignUpReq signUpReq);
}
