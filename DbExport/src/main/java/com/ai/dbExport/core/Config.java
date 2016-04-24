package com.ai.dbExport.core;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aaron on 2016/4/22.
 */
public class Config {

    static Logger log = Logger.getLogger(Config.class);

    @XmlAttribute
    private String releaseDataFilePath;

    @XmlAttribute
    private String releaseLogFilePath;

    @XmlAttribute
    private String releaseBackupPath;

    @XmlAttribute
    private String releaseChkFilePath;

    @XmlTransient
    public String getReleaseDataFilePath() {

        return releaseDataFilePath;
    }

    public void setReleaseDataFilePath(String releaseDataFilePath) {
        this.releaseDataFilePath = releaseDataFilePath;
    }

    @XmlTransient
    public String getReleaseLogFilePath() {
        return releaseLogFilePath;
    }

    public void setReleaseLogFilePath(String releaseLogFilePath) {
        this.releaseLogFilePath = releaseLogFilePath;
    }

    @XmlTransient
    public String getReleaseBackupPath() {
        return releaseBackupPath;
    }

    public void setReleaseBackupPath(String releaseBackupPath) {
        this.releaseBackupPath = releaseBackupPath;
    }

    @XmlTransient
    public String getReleaseChkFilePath() {
        return releaseChkFilePath;
    }

    public void setReleaseChkFilePath(String releaseChkFilePath) {
        this.releaseChkFilePath = releaseChkFilePath;
    }

    public Config() {
    }

    /***
     * 创建数据文件（夹）
     * @return
     */
    public File mkDataFile() {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(getReleaseDataFilePath()));
        File dataFile = new File(getReleaseDataFilePath());
        if (!dataFile.exists()) {
            if (dataFile.isFile()){
                try {
                    dataFile.createNewFile();
                } catch (IOException e) {
                    log.error("&#x521b;&#x5efa;&#x6587;&#x4ef6;&#x5931;&#x8d25;:"+e.getMessage());
                }
            }else if (dataFile.isDirectory()){
                dataFile.mkdir();
            }
        }
        return dataFile;
    }

}
