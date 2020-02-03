package com.mk.auth;

import static org.junit.Assert.assertTrue;

import com.mk.auth.common.utils.mysql.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        try
        {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection.getClientInfo().toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
