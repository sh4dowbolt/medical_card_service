package com.suraev.medical_card_service.util;

import com.suraev.medical_card_service.domain.entity.CodeDisease;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static List<CodeDisease> parse(String pathFile) {
        List<CodeDisease> diseaseList = new ArrayList<>();
        try(BufferedReader br =new BufferedReader(new FileReader(pathFile))) {
            String line;
            while((line = br.readLine()) != null) {
                CodeDisease codeDisease = parseLineToCode(line);
                diseaseList.add(codeDisease);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return diseaseList;
    }

    public static CodeDisease parseLineToCode(String line) {
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
