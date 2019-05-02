package com.example.disignmode.myhttp.myokio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class OkioServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                Socket connection = null;
                try {
                    connection = serverSocket.accept();
                    handleClientSocket(connection);
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void handleClientSocket(Socket socket) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        BufferedSource source = Okio.buffer(Okio.source(socket));
                        BufferedSink sink = Okio.buffer(Okio.sink(socket));
                        int length = source.readInt();
                        String message = source.readString(length, Charset.forName("utf-8"));
                        System.out.println("length is: " + length + " , message is : " + message);
                        if ("error exit".equals(message)) {
                            break;
                        }
                        String responseMsg = getResponseAccordMsg(message);
                        if (responseMsg != null) {
                            int respLength = responseMsg.getBytes().length;
                            sink.writeInt(respLength);
                            sink.writeString(responseMsg, Charset.forName("utf-8"));
                            sink.flush();
                        }
                        if ("error exit".equals(responseMsg)) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        thread.start();
    }

    private static String getResponseAccordMsg(String msg) {
        String result = "";
        if (msg != null && msg.length() > 0) {
            if (msg.equals("hello")) {
                result = "hello";
            } else if (msg.equals("nice to meet you")) {
                result = "nice to meet you too";
            } else if (msg.equals("see you")) {
                result = "see you next time";
            }
        }
        if (result.length() == 0) {
            result = "error exit";
        }
        return result;
    }

}
