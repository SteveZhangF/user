package com.oncore.userend.storage.uploader;

import com.oncore.userend.configure.CommonConfigure;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Created by steve on 3/6/16.
 */
public abstract class UpLoader {

    protected CommonConfigure commonConfigure;

    @Autowired
    public UpLoader(CommonConfigure commonConfigure) {
        this.commonConfigure = commonConfigure;
    }


    public abstract void init();


    public abstract  void upload(File file,String targetName);
    public abstract void upload(String content,String targetName) ;
}
