package com.example.disignmode.myhttp;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * describe :
 * date on 2019/4/26
 * author linghailong
 * email 105354999@qq.com
 */
public class mysocket {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("time.nist.gov", 13);
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append(c);
            }
            System.out.println(time);
            reader.close();
            in.close();
        } catch (IOException ex) {
            System.out.println("" + ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {

                }
            }
        }
        try(ServerSocket serverSocket=new ServerSocket(88)){
            while (true){
                Socket socket1=serverSocket.accept();
                InputStream inputStream= socket1.getInputStream();
                InputStreamReader reader1=new InputStreamReader(inputStream,"ASCII");
                for (int cur=reader1.read();cur!=-1;cur=reader1.read());
            }
        }catch (IOException e){

        }finally {

        }



    }


}
