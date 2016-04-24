package com.ai.dbExport.core;

import com.ai.common.JAXBUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by Aaron on 2016/4/22.
 */
public class DbToFileTest {


    private DbToFile dbToFile ;

    @Before
    public void setUp() throws Exception {
        InputStream is = DbToFileTest.class.getClassLoader().getResourceAsStream("DbToFile.xml");
        dbToFile = (DbToFile) JAXBUtil.xmlToBean(is,DbToFile.class);
    }



    @Test
    public void testExport() throws Exception {
        dbToFile.Export();
    }
}