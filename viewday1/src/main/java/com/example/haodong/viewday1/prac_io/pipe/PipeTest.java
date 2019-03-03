package com.example.haodong.viewday1.prac_io.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * description:
 * author: linghaoDo
 * date: 2019/3/3
 */
public class PipeTest {
    public static void main(String[] args) {
        Sender tSender = new Sender();

        Receiver tReceiver = new Receiver();

        PipedWriter out = tSender.getWriter();

        PipedReader in = tReceiver.getReader();

        try {
            //管道连接。下面2句话的本质是一样。
            //out.connect(in);
            in.connect(out);

            /**
             * Thread类的START方法：
             * 使该线程开始执行；Java 虚拟机调用该线程的 run 方法。
             * 结果是两个线程并发地运行；当前线程（从调用返回给 start 方法）和另一个线程（执行其 run 方法）。
             * 多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
             */
            tSender.start();
            tReceiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
