package com.mk.auth.common.utils.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author liumingkang
 * @Date 2020-02-03 00:25
 * @Destcription jdbc工具类
 * @Version 1.0
 **/
public class JdbcUtils
{

    private static final Logger log = LoggerFactory.getLogger(JdbcUtils.class);

    private static String driverClass = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/mk";
    private static String user = "root";
    private static String password = "lmk951010";

    /** 数据库连接池队列 使用ConcurrentQueue保持多线程安全 */
    private static Queue<Connection> used = new ConcurrentLinkedQueue<Connection>();

    private static Queue<Connection> unused = new ConcurrentLinkedQueue<Connection>();

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
     */
    public static Connection getConnection()
    {
        // TODO: 2020-02-06 暂时不设限制connection获取
        try
        {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        }
        catch (Exception e)
        {
            log.error("DB connect happend error! detail:{}", e.getMessage());
            return null;
        }
    }

    public static void close(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch (Exception e)
        {
            log.warn("Close mysql connection failed! detail:{}", e.getMessage());
        }
    }


}
