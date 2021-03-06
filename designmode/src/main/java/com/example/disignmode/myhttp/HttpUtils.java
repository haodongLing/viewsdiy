package com.example.disignmode.myhttp;

import android.text.TextUtils;

import java.io.Closeable;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class HttpUtils {
    public static String md5Url(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(url.getBytes());
            byte[] cipher = messageDigest.digest();
            for (byte b : cipher) {
                // 转成了 16 机制
                String hexStr = Integer.toHexString(b & 0xff);
                // 不足还补 0
                sb.append(hexStr.length() == 1 ? "0" + hexStr : hexStr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void  close(Closeable closeable){
        if(closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
