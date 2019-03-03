package com.example.haodong.viewday1.prac_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedWriter;
import java.io.RandomAccessFile;

/**
 * package_name: com.example.haodong.viewday1.prac_io
 * description:
 * author: linghaoDo
 * date: 2019/2/28
 */
public class Test2 {
    public static void main(String[] args) {
        try {
//            testWriter();
            testByUseFileWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void testWriter() throws IOException {
        // 创建文件字节输出流
        File file = new File("D:/test_io.txt");
        FileOutputStream fos = new FileOutputStream(file);
        // 把字节输出流转换成字符输出流，并使用了BufferedWriter提供缓冲功能
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(fos));
        bw.write("arthinking");
        bw.close();
        fos.close();

        // 创建文件字节输入流
        FileInputStream fis = new FileInputStream(file);
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

    public static void testByUseFileWriter() throws IOException {
        String str = "hello world!!!";
        //创建字符数组并初始化
        char[] buffer = new char[str.length()];
        /*获取单个字符，并且加入到字符缓存区*/
        str.getChars(0, str.length(), buffer, 0);
        File file = new File("d:/filewriter_test.txt");
        FileWriter fileWriter = new FileWriter(file);
        // 逐个字符的输出到文件
        for (int i = 0; i < buffer.length; i++) {
            fileWriter.write(buffer[i]);
        }
        fileWriter.close();

        // 创建FileReader
        BufferedReader br = new BufferedReader(
                new FileReader(file));
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
        File file = new File("D:/arthinking.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        //写入数据
        raf.writeInt(1);
        raf.writeChar('a');
        //文件指针复位
        raf.seek(0);
        //输出数据
        System.out.println(raf.readInt() + "" + raf.readChar());
    }

    /**
     * CharArrayWriter的API测试函数
     */
    private static void tesCharArrayWriter() {
         final int LEN = 5;
        // 对应英文字母“abcdefghijklmnopqrstuvwxyz”
         final char[] ArrayLetters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z'};
        try {
            // 创建CharArrayWriter字符流
            CharArrayWriter caw = new CharArrayWriter();

            // 写入“A”个字符
            caw.write('A');
            // 写入字符串“BC”个字符
            caw.write("BC");
            //System.out.printf("caw=%s\n", caw);
            // 将ArrayLetters数组中从“3”开始的后5个字符(defgh)写入到caw中。
            caw.write(ArrayLetters, 3, 5);
            //System.out.printf("caw=%s\n", caw);

            // (01) 写入字符0
            // (02) 然后接着写入“123456789”
            // (03) 再接着写入ArrayLetters中第8-12个字符(ijkl)
            caw.append('0').append("123456789").append(String.valueOf(ArrayLetters), 8, 12);

            System.out.printf("caw=%s\n", caw);

            // 计算长度
            int size = caw.size();
            System.out.printf("size=%s\n", size);

            // 转换成byte[]数组
            char[] buf = caw.toCharArray();
            System.out.printf("buf=%s\n", String.valueOf(buf));

            // 将caw写入到另一个输出流中
            CharArrayWriter caw2 = new CharArrayWriter();
            caw.writeTo(caw2);
            System.out.printf("caw2=%s\n", caw2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
