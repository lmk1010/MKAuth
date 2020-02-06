package com.mk.auth.common.dao;

import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.common.utils.mysql.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author liumingkang
 * @Date 2020-02-06 10:45
 * @Destcription CommonSDK统一操作DB实现
 * @Version 1.0
 **/
public class CommonDao
{
    private static final Logger log = LoggerFactory.getLogger(CommonDao.class);

    // TODO: 2020-02-06 未使用预编译执行
    public static ResultSet excuteQuerySQL(String sql) throws Exception
    {
        try
        {
            Connection con = JdbcUtils.getConnection();
            if (null == con)
            {
                log.error("Excute sql failed! db connection get failed!");
                throw new MKRuntimeException();
            }
            Statement statement = con.createStatement();
            return statement.executeQuery(sql);
        }
        catch (SQLException e)
        {
            log.error("SQL exception happend! detail:{}", e.getMessage());
            throw e;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public static void excuteInsertString(String sql)
    {

    }


}
