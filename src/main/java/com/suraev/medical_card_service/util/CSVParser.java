package com.suraev.medical_card_service.util;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
@Component
public class CSVParser {

    public static List<CodeDisease> parse(String target) {
        URL url = null;
        try {
            url = new URL(target);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        List<CodeDisease> diseaseList = new ArrayList<>();

        URLConnection connection= null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(InputStreamReader inputStreamReader= new InputStreamReader(connection.getInputStream());
            BufferedReader br =new BufferedReader(inputStreamReader)) {
            String line;
            while((line = br.readLine()) != null) {
                CodeDisease codeDisease = parseLine(line);
                if(codeDisease.getId().isEmpty() || codeDisease.getTitleDisease().isEmpty()) {
                    continue;
                }
                diseaseList.add(codeDisease);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return diseaseList;
    }

    public static CodeDisease parseLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean insideQuotes = false;
        // "4","0101A000","A00.0","Холера, вызванная холерным вибрионом 01, биовар cholerae","3",,1,
        for(char ch: line.toCharArray()) {
            if(ch == '\"') {
                insideQuotes = !insideQuotes;
            } else if(ch == ',' && !insideQuotes) {
                result.add(sb.toString());
                sb = new StringBuilder();
            } else {
              sb.append(ch);
            }
        }
        result.add(sb.toString());

        return CodeDisease.builder().id(result.get(2)).titleDisease(result.get(3)).build();
    }
}
