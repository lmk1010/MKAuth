package com.mk.auth.core.dao;

import com.mk.auth.core.entity.AuthRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-01 22:50
 * @Destcription 角色实体类
 * @Version 1.0
 **/
@Mapper
@Repository("roleDao")
public interface RoleDao
{
    @Select("select * from mk_auth_user")
    @Results(id = "resultMap" , value = {
            @Result(property = "id", column = "id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "roleDescription", column = "role_description")
    })
    List<AuthRole> selectAll();

    @Select("select * from mk_auth_role where id in (select role_id from mk_auth_user_role where user_id = #{userId})")
    @ResultMap("resultMap")
    List<AuthRole> selectByUser(@Param("userId") int userId);



}
