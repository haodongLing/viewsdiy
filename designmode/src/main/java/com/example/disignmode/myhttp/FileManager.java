package com.example.disignmode.myhttp;

import android.content.Context;

import java.io.File;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class FileManager {
    private File mRootDir;
    private Context mContext;
    private static final FileManager sManager = new FileManager();

    public static FileManager getManager() {
        return sManager;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
    }

    /**
     * 判断文件是否存在，如果不存在，直接创建
     * @param file
     */
    public void rootDir(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }

        if (file.exists() && file.isDirectory()) {
            mRootDir = file;
        }
    }
    /**
     * 通过网络的路径获取一个本地文件路径
     * @param url
     * @return
     */
    public File getFile(String url) {
        String fileName = HttpUtils.md5Url(url);
        if(mRootDir == null){
            mRootDir = mContext.getCacheDir();
        }
        File file = new File(mRootDir,fileName);
        return file;
    }
}
