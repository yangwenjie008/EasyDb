package com.ai.dbExport.core;

import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aaron on 2016/4/22.
 */

@XmlRootElement(name = "DbToFile")
public class DbToFile {

    static Logger log = Logger.getLogger(DbToFile.class);

    @XmlElement(name = "Task")
    private List<Task> tasks;

    @XmlTransient
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public DbToFile() {
    }

    /*****************************************************
     * 功能函数
     *****************************************************/

    public void Export() throws IOException, SQLException, InterruptedException {
        log.info(Thread.currentThread().getName()+"---> DbToFile export方法开始");
        Preconditions.checkNotNull(this.getTasks(),"Task不能为空，请检查DbToFile.xml文件");
        for (Task task : this.getTasks()){
            task.export();
        }
    }
}
