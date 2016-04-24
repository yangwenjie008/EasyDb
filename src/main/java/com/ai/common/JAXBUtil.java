package com.ai.common;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Aaron on 2016/4/21.
 */
public class JAXBUtil {

    Logger log = Logger.getLogger(getClass());

    public static Object xmlToBean(File file,Class clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }

    public static void beanToXml(Class clazz, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(clazz,file);
    }
}
