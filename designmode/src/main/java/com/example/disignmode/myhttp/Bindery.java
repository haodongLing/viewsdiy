package com.example.disignmode.myhttp;

import java.io.IOException;
import java.io.OutputStream;

/**
 * describe :
 * date on 2019/4/29
 * author linghailong
 * email 105354999@qq.com
 */
public interface Bindery {
    long fileLength();

    String mimType();

    String fileName();

    void onWrite(OutputStream outputStream)throws IOException;
}
