package com.app.shopping.util;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Test {
    final static String PATH = "C:\\Users\\82638\\Desktop\\All.csv";

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>(1024);
//        try (FileReader stream = new FileReader(new File(PATH))) {
//            String str;
//            BufferedReader bf = new BufferedReader(stream);
//            while ((str = bf.readLine()) != null) {
//                String[] split = str.split(",");//0 做v 1做k
//                if (map.containsKey(split[1])) {
//                    System.out.println(split[1] + "---" + split[0]);
//                }
//                map.put(split[1], split[0]);
//            }
//
//
//            System.out.println();
//        }
        try (InputStream inputStream = new FileInputStream(new File(PATH))) {
            IOUtils.readLines(inputStream).forEach(e -> {
                String[] split = e.split(",");//0 做v 1做k
                if (map.containsKey(split[1])) {
                    System.out.println(split[1] + "---" + split[0]);
                }
                map.put(split[1], split[0]);
            });
        }
    }


    private static void change(String str, Map<String, String> map) {
        String[] split = str.split(",");//0 做v 1做k
        if (map.containsKey(split[1])) {
            System.out.println(split[1] + "---" + split[0]);
        }
        map.put(split[1], split[0]);
    }

}
