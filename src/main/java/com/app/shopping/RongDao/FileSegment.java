package com.app.shopping.RongDao;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileSegment {

    private static String fileType = ".txt";
    private static String path = "E:\\RongDa\\fileMain" + fileType;
    private static File fileName = new File(path);
    private static String newFile = "E:\\RongDa\\NewFileMain";
    private static String segmentPath = "E:\\RongDa\\";


    /**
     * 创建文本文件.
     *
     * @throws IOException
     */
    public static void creatTxtFile() throws IOException {
        if (!fileName.exists()) {
            RandomAccessFile file = new RandomAccessFile(path, "rw");
            file.setLength(1024 * 1024 * 100);
            System.err.println(path + "已创建！");
        }
    }

    /**
     * 分割文件
     *
     * @param src
     * @param m
     */
    private static void splitFileDemo(File src, int m) {
        if (src.isFile()) {
            //获取文件的总长度
            long l = src.length();
            long size = l / m;
            //获取文件名
            String fileName = src.getName().substring(0, src.getName().indexOf("."));
            //获取文件后缀
            String endName = src.getName().substring(src.getName().lastIndexOf("."));
            System.out.println(endName);
            InputStream in = null;
            try {
                in = new FileInputStream(src);
                for (int i = 1; i <= m; i++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(src.getParent()).append("\\").append(fileName)
                            .append("_data").append(i).append(endName);
                    System.out.println(sb.toString());
                    File file2 = new File(sb.toString());
                    //创建写文件的输出流
                    OutputStream out = new FileOutputStream(file2);
                    int len = -1;
                    byte[] bytes = new byte[5 * 1024 * 1024];
                    while ((len = in.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                        if (file2.length() >= size) {
                            break;
                        }
                    }
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件合并的方法（传入要合并的文件路径）
     *
     * @param src
     */
    //文件合并的方法（传入要合并的文件路径）
    private static void joinFileDemo(String... src) throws FileNotFoundException {
        File n = new File(newFile + fileType);
        if (!n.exists()) {
            RandomAccessFile file = new RandomAccessFile(newFile + fileType, "rw");
        }

        for (int i = 0; i < src.length; i++) {
            File file = new File(segmentPath + src[i]);
            String fileName = file.getName().substring(0, file.getName().indexOf("_"));
            String endName = file.getName().substring(file.getName().lastIndexOf("."));

            StringBuffer sb = new StringBuffer();
            sb.append(newFile).append(endName);
            System.out.println(sb.toString());
            try {
                //读取小文件的输入流
                InputStream in = new FileInputStream(file);
                //写入大文件的输出流
                File file2 = new File(sb.toString());
                OutputStream out = new FileOutputStream(file2, true);
                int len = -1;
                byte[] bytes = new byte[10 * 1024 * 1024];
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件合并完成！");
    }

    public static String[] getFileNames(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();
        for (File f : files) {
            if (f.isFile() && f.getName().contains("_")) {
                fileNames.add(f.getName());
            }
        }
        String[] strings = fileNames.toArray(new String[0]);
        return strings;
    }

    public static LinkedList<String> makeSHA1(String filePath) throws Exception {
        File file = new File(filePath);
        //读取文件的输入流
        InputStream in = new FileInputStream(file);
        LinkedList<String> list = new LinkedList<>();
        int len = -1;
        byte[] bytes = new byte[10 * 1024 * 1024];
        while ((len = in.read(bytes)) != -1) {
            list.add(shaEncode(new String(bytes)));
        }
        return list;
    }

    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static boolean compare(LinkedList listOld, LinkedList listNew) {
        int length;
        if ((length = listOld.size()) == listNew.size()) {
            for (int i = 0; i < length; i++) {
                if (!listNew.get(i).equals(listOld.get(i))) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }


    public static void main(String[] args) {
        try {
            //创建100M的文件
            creatTxtFile();
            //分割文件
            splitFileDemo(fileName, 20);
            //合并文件为新文件
            joinFileDemo(getFileNames(segmentPath));
            //进行加密算法
            LinkedList<String> listOld = makeSHA1(path);
            LinkedList<String> listNew = makeSHA1(newFile + fileType);
            boolean compare = compare(listOld, listNew);
            if (compare) {
                System.out.println("一致");
            } else {
                System.out.println("不一致");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
