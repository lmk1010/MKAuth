package com.mk.auth.core.dao;

import com.mk.auth.core.entity.AuthUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-01 15:11
 * @Destcription 用户DAO层
 * @Version 1.0
 **/
@Mapper
@Repository("userDao")
public interface UserDao
{
    @Insert("insert into mk_auth_user(auth_name,auth_pass,enabled,credentials_non_expired,account_non_expired,account_non_locked,create_time,update_time) values(#{authUser.authName},#{authUser.authPass},#{authUser.enabled},#{authUser.credentialsNonExpired},#{authUser.accountNonExpired},#{authUser.accountNonLocked},#{authUser.createTime},#{authUser.updateTime})")
    void insert(@Param("authUser") AuthUser authUser);

    @Select("select * from mk_auth_user")
    @Results(id = "resultMap" , value = {
            @Result(property = "id", column = "id"),
            @Result(property = "authName", column = "auth_name"),
            @Result(property = "authPass", column = "auth_pass"),
            @Result(property = "memCID", column = "mem_cid"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "credentialsNonExpired", column = "credentials_non_expired"),
            @Result(property = "accountNonExpired", column = "account_non_expired"),
            @Result(property = "accountNonLocked", column = "account_non_locked"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<AuthUser> selectAll();

    @Select(("select * from mk_auth_user where auth_name = #{authName}"))
    @ResultMap("resultMap")
    AuthUser selectByName(String authName);
}
