package com.suraev.medical_card_service.util;

import com.suraev.medical_card_service.service.ParseCodeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadFileUtil {

    public static void downloadFile(String target, String source) {
        Logger log = LoggerFactory.getLogger(DownloadFileUtil.class);
        //TODO: обработать прям здесь или пробросить ввыше
        URL url = null;
        try {
            url = new URL(target);
        } catch (MalformedURLException e) {
            log.error("Url for upload file for CodeDiseaseDb is uncorrected! Check the syntax!");
            throw new RuntimeException(e);
        }
        try(
                InputStream in = new BufferedInputStream(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(source)){;
            byte [] dataBuffer = new byte[1024];
            int bytesRead;
            while((bytesRead=in.read(dataBuffer,0,1024)) != -1) {
                fileOutputStream.write(dataBuffer,0,bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
