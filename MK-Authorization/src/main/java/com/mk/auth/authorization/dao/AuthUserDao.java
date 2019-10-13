package com.mk.auth.authorization.dao;

import com.mk.auth.authorization.entity.AuthUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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

    @Results({ //2
            @Result(property = "id", column = "id"), //2
            @Result(property = "authName", column = "auth_name"),
            @Result(property = "authPass", column = "auth_pass"),
            @Result(property = "memCID", column = "mem_cid"),
            @Result(property = "authorities", column = "authorities")
    })

    @Insert("insert into mk_auth_user(auth_name,auth_pass,authorities) values(#{authName},#{authPass},#{authorities})")
    void insertAuthUser(@Param("authName") String authName,
                        @Param("authPass") String authPass,
                        @Param("authorities") String authorities);

    @Select("select id,auth_name.auth_pass,mem_cid,authorities from mk_auth_user")
    List<AuthUser> selectAuthUsers();


}
