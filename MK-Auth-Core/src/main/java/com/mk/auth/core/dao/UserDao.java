package com.mk.auth.core.dao;

import com.mk.auth.core.entity.AuthUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-01 15:11
 * @Destcription TODO
 * @Version 1.0
 **/
@Mapper
@Repository("userDao")
public interface UserDao
{
    @Insert("insert into mk_auth_user(auth_name,auth_pass,authorities) values(#{authName},#{authPass},#{authorities})")
    void insert(@Param("authName") String authName,
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
    List<AuthUser> selectAll();

    @Select(("select * from mk_auth_user where auth_name = #{authName}"))
    @ResultMap("resultMap")
    AuthUser selectByName(String authName);
}
