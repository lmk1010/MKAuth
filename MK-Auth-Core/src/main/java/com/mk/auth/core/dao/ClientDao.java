package com.mk.auth.core.dao;

import com.mk.auth.core.entity.AuthClient;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-02 11:37
 * @Destcription TODO
 * @Version 1.0
 **/
@Mapper
@Repository("clientDao")
public interface ClientDao
{
    @Insert("insert into mk_auth_client(client_name,client_pass,client_key,client_authorities,locked,update_time,create_time) values(#{authClient.clientName},#{authClient.clientPass},#{authClient.clientKey},#{authClient.clientAuthorities},#{authClient.locked},#{authClient.updateTime},#{authClient.createTime})")
    void insert(@Param("authClient") AuthClient authClient);

    @Select("select * from mk_auth_client")
    @Results(id = "resultMap" , value = {
            @Result(property = "id", column = "id"),
            @Result(property = "clientName", column = "client_name"),
            @Result(property = "clientPass", column = "client_pass"),
            @Result(property = "clientKey", column = "client_key"),
            @Result(property = "clientAuthorities", column = "client_authorities"),
            @Result(property = "locked", column = "locked"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<AuthClient> selectAll();

    @Select("select * from mk_auth_client where client_name = #{clientName}")
    @ResultMap("resultMap")
    AuthClient selectByName(@Param("clientName") String clientName);
}
