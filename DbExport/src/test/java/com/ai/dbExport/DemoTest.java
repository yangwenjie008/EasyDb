package com.ai.dbExport;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Aaron on 2016/4/21.
 */
public class DemoTest {

    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        DataSource dataSource = (DruidDataSource) applicationContext.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user_t");
        while (resultSet!=null && resultSet.next()){
            System.out.println(resultSet.getInt("id"));
        }
    }
}
