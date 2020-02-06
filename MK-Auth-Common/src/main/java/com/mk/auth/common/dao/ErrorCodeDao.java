package com.mk.auth.common.dao;

import com.mk.auth.common.entity.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

/**
 * @Author liumingkang
 * @Date 2020-02-02 23:56
 * @Destcription
 * @Version 1.0
 **/
public class ErrorCodeDao
{

    public static ErrorCode findByCode(String code) throws Exception
    {
        ResultSet resultSet = CommonDao.excuteQuerySQL("select * from mk_errorcode where code = " + code + " limit 1");

        return convertResult(resultSet);
    }


    public static ErrorCode convertResult(ResultSet rs) throws Exception
    {
        ErrorCode errorCode = new ErrorCode();
        while (rs.next())
        {
            errorCode.setCode(rs.getString("code"));
            errorCode.setCause(rs.getString("cause"));
            errorCode.setErrorMsg(rs.getString("error_msg"));
            errorCode.setSolution(rs.getString("solution"));
        }
        return errorCode;
    }

}
