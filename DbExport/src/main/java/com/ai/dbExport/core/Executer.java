package com.ai.dbExport.core;

import javax.sql.DataSource;

/**
 * Created by Aaron on 2016/4/24.
 */
public class Executer implements Runnable {

    private SqlWarpper sql;

    private DataSource dataSource;

    Executer(SqlWarpper sql, DataSource dataSource){
        this.sql = sql;
        this.dataSource = dataSource;
    }

    public void run() {

    }
}
