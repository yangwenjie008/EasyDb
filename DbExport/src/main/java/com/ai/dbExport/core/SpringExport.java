package com.ai.dbExport.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Aaron on 2016/4/22.
 */
public class SpringExport implements IExport {

    Logger log = Logger.getLogger(getClass());

    static ApplicationContext applicationContext;

    static {
         applicationContext = new ClassPathXmlApplicationContext("bean.xml");
    }

    public DataSource getDSource(String dataSourceId) {
        DataSource dataSource = (DataSource) applicationContext.getBean(dataSourceId);
        if (dataSource != null)
            return dataSource;
        log.info("数据源为null，请检查bean.xml文件");
        return null;
    }

    public void export(DataSource dataSource, List<String> sqls){
        Connection connection;
        Preconditions.checkArgument(sqls!=null && sqls.size()>0);
        try {
            for (String sql : sqls) {
                connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                Preconditions.checkNotNull(resultSet);
                while(resultSet.next()){

                }
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
