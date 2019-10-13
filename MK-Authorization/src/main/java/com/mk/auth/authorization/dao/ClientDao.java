package com.mk.auth.authorization.dao;

import com.mk.auth.authorization.entity.Client;
import com.sun.xml.bind.v2.model.core.ID;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2019-10-13 13:28
 * @Destcription oauth2客户端的DAO
 * @Version 1.0
 **/
@Mapper
@Repository("clientDao")
public interface ClientDao
{

    @Insert("insert into mk_auth_client(client_name,grand_type,authorities,client_pass,redirect_url,scope,auto_approve,access_token_validity_sec,refresh_token_validity_sec,additional_info) " +
            "values(#{clientName},#{grandTypes},#{authorities},#{clientPass},#{redirectUrl},#{scope},#{autoApprove},#{accessTokenValiditySeconds},#{refreshTokenValiditySeconds},#{additionalInformation})")
    void insertClient(Client client);

    @Select("select * from mk_auth_client")
    @Results(id = "resultMap",value = {
            @Result(column = "client_name",property = "clientName"),
            @Result(column = "grand_type",property = "grandTypes"),
            @Result(column = "authorities",property = "authorities"),
            @Result(column = "client_pass",property = "clientPass"),
            @Result(column = "redirect_url",property = "redirectUrl"),
            @Result(column = "scope",property = "scope"),
            @Result(column = "auto_approve",property = "autoApprove"),
            @Result(column = "access_token_validity_sec",property = "accessTokenValiditySeconds"),
            @Result(column = "refresh_token_validity_sec",property = "refreshTokenValiditySeconds"),
            @Result(column = "additional_info",property = "additionalInformation")
    })
    List<Client> selectClients();

    @Select("select * from mk_auth_client where client_name = #{clientName}")
    @ResultMap("resultMap")
    Client selectClient(@Param("clientName") String clientName);

}
