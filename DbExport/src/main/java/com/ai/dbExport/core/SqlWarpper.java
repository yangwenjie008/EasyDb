package com.ai.dbExport.core;

import com.ai.common.Constant;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;

/**
 * Created by Aaron on 2016/4/22.
 */
public class SqlWarpper {

    Logger log = Logger.getLogger(getClass());

    @XmlAttribute
    private String action;
    @XmlAttribute
    private String table;
    @XmlElement(name = "column")
    private String columns;

    @XmlTransient
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @XmlTransient
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @XmlTransient
    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public SqlWarpper() {
    }

    public String generSqlString(){

        Preconditions.checkArgument(!Strings.isNullOrEmpty(this.getAction()));
        Preconditions.checkArgument(!Strings.isNullOrEmpty(this.getColumns()));
        Preconditions.checkArgument(!Strings.isNullOrEmpty(this.getTable()));

        if (Constant.SELECT.equalsIgnoreCase(this.getAction())){
            String sqlStr = this.getAction()+" "+this.getColumns().trim()+" from "+this.getTable();
            if (log.isDebugEnabled()){
                log.debug(Thread.currentThread().getName()+"--> 获取SQL :" + sqlStr);
            }
            return sqlStr;
        } /*else if (Constant.INSERT.equalsIgnoreCase(this.getAction())){
            return "insert into "+this.getTable()+" ("+this.getColumn().trim()+") "+this.getTable();
        } else if (Constant.DELETE.equalsIgnoreCase(this.getAction())){

        }*/
        return null;
    }
}
