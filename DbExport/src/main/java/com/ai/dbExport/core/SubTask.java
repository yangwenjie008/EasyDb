package com.ai.dbExport.core;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Aaron on 2016/4/22.
 */
public class SubTask {

    static Logger log = Logger.getLogger(SubTask.class);

    @XmlElement(name = "SQL")
    @XmlElementWrapper(name = "SQLList")
    private volatile List<SqlWarpper> sqls;

    @XmlElement(name = "Config")
    private volatile Config config;

    @XmlTransient
    public List<SqlWarpper> getSqls() {
        return sqls;
    }

    public void setSqls(List<SqlWarpper> sqls) {
        this.sqls = sqls;
    }

    @XmlTransient
    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public SubTask() {

    }

    private volatile DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //TODO:重构，根据指定规则生成文件
    public File mkDatFileWithRule() {
        Preconditions.checkNotNull(getConfig());
        Preconditions.checkArgument(!Strings.isNullOrEmpty(getConfig().getReleaseDataFilePath()));
        File file = getConfig().mkDataFile();
        return file;
    }

    public void export() throws SQLException, IOException, InterruptedException {
        log.info(Thread.currentThread().getName()+"---> SubTask export 方法开始");
        Preconditions.checkArgument(getSqls() != null && getSqls().size() > 0, "SQLList 不能为空，请检查DbToFile.xml");
        File file = mkDatFileWithRule();
        for (int i = 0; i < getSqls().size(); i++) {
            exec(getSqls().get(i),file);
        }
        log.info(Thread.currentThread().getName()+"---> SubTask export 方法结束");
    }

    public void exec(SqlWarpper sql, File file) throws SQLException, IOException {
        log.info(Thread.currentThread().getName()+"---> 开始执行exec方法");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql.generSqlString());
        while (resultSet != null && resultSet.next()) {
            String[] columns = sql.getColumns().split(",");
            StringBuffer result = new StringBuffer();
            for (String column : columns) {
                result.append(resultSet.getString(column));
                result.append(",");
            }
            String content = result.toString().substring(0, result.toString().length() - 1) + "\n";
            Files.append(content, file, Charset.forName("UTF-8"));
            log.info(Thread.currentThread().getName() + "---> 将【" + content + "】写入文件" + file.getAbsolutePath());
        }
        resultSet.close();
        statement.close();
        connection.close();
        log.info(Thread.currentThread().getName()+" ---> 结束");
    }

}
