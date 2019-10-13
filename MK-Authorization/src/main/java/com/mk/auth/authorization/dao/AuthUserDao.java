package com.mk.auth.authorization.dao;

import com.mk.auth.authorization.entity.AuthUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.net.IDN;
import java.util.List;

/**
 * @Author liumingkang
 * @Date 2019-10-13 10:25
 * @Destcription 权限用户的DAO
 * @Version 1.0
 **/
@Mapper
@Repository("authUserDao")
public interface AuthUserDao
{

    @Insert("insert into mk_auth_user(auth_name,auth_pass,authorities) values(#{authName},#{authPass},#{authorities})")
    void insertAuthUser(@Param("authName") String authName,
                        @Param("authPass") String authPass,
                        @Param("authorities") String authorities);

    @Select("select * from mk_auth_user")
    @Results(id = "resultMap" , value = { //2
            @Result(property = "id", column = "id"), //2
            @Result(property = "authName", column = "auth_name"),
            @Result(property = "authPass", column = "auth_pass"),
            @Result(property = "memCID", column = "mem_cid"),
            @Result(property = "authorities", column = "authorities")
    })
    List<AuthUser> selectAuthUsers();

    @Select(("select * from mk_auth_user where auth_name = #{authName}"))
    @ResultMap("resultMap")
    AuthUser selectAuthUser(String authName);


}
