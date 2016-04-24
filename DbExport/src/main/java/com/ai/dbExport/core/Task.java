package com.ai.dbExport.core;

import com.google.common.base.Preconditions;

import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aaron on 2016/4/22.
 */
public class Task {

    private IExport export;

    @XmlElement(name = "DbSourceId")
    private String DSString;

    @XmlElement(name = "SubTask")
    private List<SubTask> subTasks;

    @XmlTransient
    public String getDSString() {
        return DSString;
    }

    public void setDSString(String DSString) {
        this.DSString = DSString;
    }

    @XmlTransient
    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public Task() {
        this.export = new SpringExport();
    }

    /*********************************************************************
     * 功能函数
     *********************************************************************/

    public DataSource getDataSource() {
        return export.getDSource(this.getDSString());
    }

    public void export() throws IOException, SQLException, InterruptedException {
        Preconditions.checkNotNull(getDataSource());
        Preconditions.checkArgument(getSubTasks() != null && getSubTasks().size() > 0, "SubTasks 不能为空");
        for (SubTask subtask : getSubTasks()) {
            subtask.setDataSource(getDataSource());
            subtask.export();
        }
    }
}
