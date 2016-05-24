package com.oncore.userend.helper;

import com.oncore.userend.configure.CommonConfigure;
import com.oncore.userend.storage.downloader.DownLoader;
import com.oncore.userend.storage.uploader.UpLoader;

import java.io.IOException;
import java.util.Map;

/**
 * Created by steve on 3/27/16.
 */
public class HtmlReportGenerator extends ReportGenerator {


    public HtmlReportGenerator(CommonConfigure commonConfigure, DownLoader downLoader, UpLoader upLoader) {
        super(commonConfigure, downLoader, upLoader);

    }

    @Override
    public String[] getReport(String templateName, Map element) throws IOException {

        return null;
    }

    @Override
    public String getReport(String path) {
        return null;
    }
}
