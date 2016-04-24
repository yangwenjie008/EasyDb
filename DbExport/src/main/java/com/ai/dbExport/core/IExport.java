package com.ai.dbExport.core;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Aaron on 2016/4/22.
 */
public interface IExport {

    DataSource getDSource(String dataSourceId);

    void export(DataSource dataSource, List<String> sql);
}
