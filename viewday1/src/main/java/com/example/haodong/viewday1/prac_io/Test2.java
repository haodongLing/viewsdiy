package com.example.haodong.viewday1.prac_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

/**
 * package_name: com.example.haodong.viewday1.prac_io
 * description:
 * author: linghaoDo
 * date: 2019/2/28
 */
public class Test2 {
    public static void main(String[] args) {

    }

    public void testWriter() throws IOException {
        // 创建文件字节输出流
        FileOutputStream fos = new FileOutputStream("D:/test_io.txt");
        // 把字节输出流转换成字符输出流，并使用了BufferedWriter提供缓冲功能
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(fos));
        bw.write("arthinking");
        bw.close();
        fos.close();

        // 创建文件字节输入流
        FileInputStream fis = new FileInputStream("D:/itzhai/arthinking.txt");
        // 把字节输入流转换成字符输入流，并使用了BufferedReader提供缓冲功能
        BufferedReader br = new BufferedReader(
                new InputStreamReader(fis));
        String str = br.readLine();
        while (null != str) {
            System.out.println(str);
            str = br.readLine();
        }
        br.close();
        fis.close();
    }

    public void testByUseFileWriter() throws IOException {
        String str = "hello world!!!";
        //创建字符数组并初始化
        char[] buffer = new char[str.length()];
        /*获取单个字符，并且加入到字符缓存区*/
        str.getChars(0, str.length(), buffer, 0);
        FileWriter fileWriter = new FileWriter("d:/filewriter_test.txt");
        // 逐个字符的输出到文件
        for (int i = 0; i < buffer.length; i++) {
            fileWriter.write(buffer[i]);
        }
        fileWriter.close();

        // 创建FileReader
        BufferedReader br = new BufferedReader(
                new FileReader("d:/filewriter_test.txt"));
        // 使用BufferedReader提供的逐行读取函数读取文件
        while (null != (str = br.readLine())) {
            System.out.println(str);
        }
        br.close();

    }

    public void testByUseCharArr() throws IOException {
        String str = "arthinking";
        // 创建并初始化字符数组
        char[] ch = new char[str.length()];
        str.getChars(0, str.length(), ch, 0);

        // 通过字符数组初始化字符数组输入流
        CharArrayReader cr = new CharArrayReader(ch);

        int c;
        while (-1 != (c = cr.read())) {
            System.out.print((char) c);
        }
    }

    public void testByRandomAccessFile() throws IOException {
        // 创建随机访问文件
        /* @param      name   the system-dependent filename
         * @param      mode   the access <a href="#mode">mode</a>*/
        RandomAccessFile raf = new RandomAccessFile("D:arthinking.txt", "rw");
        //写入数据
        raf.writeInt(1);
        raf.writeChar('a');
        //文件指针复位
        raf.seek(0);
        //输出数据
        System.out.println(raf.readInt() + "" + raf.readChar());
    }


}
