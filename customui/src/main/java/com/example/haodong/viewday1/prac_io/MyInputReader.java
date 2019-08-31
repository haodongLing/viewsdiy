package com.example.haodong.viewday1.prac_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

/**
 * package_name: com.example.haodong.viewday1.prac_io
 * description:
 * author: linghaoDo
 * date: 2019/2/28
 */
public class MyInputReader {
    private static final int BUFFER_SIZE = 512;
    public static void main(String[] args) {
        File file = new File("c:\\test.txt");
        BufferedReader read = null;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("c:\\zwm.txt"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            read = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = read.readLine()) != null) {
                writer.append(tempString);
                writer.newLine();//换行
                writer.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
            }
            read.close();
            writer.close();
            System.out.println("文件写入完成...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        System.out.println("以字符为单位读取文件内容，一次读一个字符：");
        /*一次读一个字符*/
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字符：");
            // 一次读多个字符
            char[] tempchars = new char[BUFFER_SIZE];
            int charread = 0;
            //由于要以字符来读取，所以需要套上字符流
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
//    public static class CloseUtils {
//        public static void setClose(T<? extends Closeable> t) {
//
//        }
//    }
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader bufferedReader = null;
        System.out.println("以行为单位读取内容");
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String tempString = null;
            int line = 1;
            while ((tempString = bufferedReader.readLine()) != null) {
                System.out.println("line " + line + ":" + tempString);
                line++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字符流的基本使用
     *
     * @throws IOException
     */
    public void test1() throws IOException {

        FileOutputStream fos = new FileOutputStream("d:/data.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        String str1 = "中国移动阅读基地";
        String str2 = "中国移动视频基地";
        bw.write(str1);
        /*换行*/
        bw.write("\r\n");
        bw.write(str2);
        /*关闭一般是从外到内*/
        bw.close();
        osw.close();
        fos.close();

    }

}
