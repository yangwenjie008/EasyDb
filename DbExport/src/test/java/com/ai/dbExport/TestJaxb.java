package com.ai.dbExport;

import com.ai.common.JAXBUtil;
import com.ai.dbExport.core.*;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 2016/4/21.
 */
public class TestJaxb {

    @Test
    public void XmlToBean() throws JAXBException {
        DbToFile dbToFile = (DbToFile) JAXBUtil.xmlToBean(new File("abcd1.xml"),DbToFile.class);
        String columns = dbToFile.getTasks().get(0).getSubTasks().get(0).getSqls().get(0).getColumns();
        String action = dbToFile.getTasks().get(0).getSubTasks().get(0).getSqls().get(0).getAction();
        String table = dbToFile.getTasks().get(0).getSubTasks().get(0).getSqls().get(0).getTable();
        System.out.println(columns);
        System.out.println(action);
        System.out.println(table);
    }

    @Test
    public void  beanToXml1(){

        Config config = new Config();
        config.setReleaseBackupPath("This is a ReleaseBackupPath");
        config.setReleaseChkFilePath("This is a ReleaseChkFilePath");
        config.setReleaseDataFilePath("This is a ReleaseDataFilePath");
        config.setReleaseLogFilePath("This is a ReleaseLogFilePath");

        List<SqlWarpper> sqls = new ArrayList<SqlWarpper>();
        SqlWarpper sqlWarpper = new SqlWarpper();
        sqlWarpper.setAction("select");
        sqlWarpper.setTable("user_t");
        sqlWarpper.setColumns("id,user_name,password,age");
        sqls.add(sqlWarpper);

        SubTask subTask = new SubTask();
        subTask.setConfig(config);
        subTask.setSqls(sqls);

        List<SubTask> subTasks = new ArrayList<SubTask>();
        subTasks.add(subTask);

        Task task = new Task();
        task.setDSString("mysqlDataSource");
        task.setSubTasks(subTasks);

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task);

        DbToFile dbToFile = new DbToFile();
        dbToFile.setTasks(tasks);

        try {
            JAXBUtil.beanToXml(dbToFile,new File("abcd1.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
