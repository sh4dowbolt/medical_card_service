package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseCodeServiceImpl {
    @Value("${source_link_for_update_code_dictionary}")
    private String target;
    @Value("${path_to_save_files}")
    private String source;




    public static void downloadFile(String target, String source) throws MalformedURLException {
        URL url = new URL(target);
        try(
            InputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(source)){;
            byte [] dataBuffer = new byte[1024];
            int bytesRead;
            while((bytesRead=in.read(dataBuffer,0,2014)) != -1) {
                fileOutputStream.write(dataBuffer,0,bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
