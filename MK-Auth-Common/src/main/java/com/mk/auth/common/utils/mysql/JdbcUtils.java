package com.mk.auth.common.utils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author liumingkang
 * @Date 2020-02-03 00:25
 * @Destcription jdbc工具类
 * @Version 1.0
 **/
public class JdbcUtils
{
    private static String driverClass = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/mk";
    private static String user = "root";
    private static String password = "lmk951010";

    static
    {
        try
        {
            Class.forName(driverClass);//注册加载驱动
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取 Connetion
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
