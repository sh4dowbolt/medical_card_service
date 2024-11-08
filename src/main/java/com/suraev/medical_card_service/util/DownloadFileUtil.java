package com.suraev.medical_card_service.util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadFileUtil {
    public static void downloadFile(String target, String source) throws MalformedURLException {
        URL url = new URL(target);
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
