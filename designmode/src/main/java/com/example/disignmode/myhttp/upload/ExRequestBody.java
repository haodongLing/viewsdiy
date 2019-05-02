package com.example.disignmode.myhttp.upload;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

/**
 * describe : 上传进度监听
 * date on 2019/5/1
 * author linghailong
 * email 105354999@qq.com
 */
public class ExRequestBody extends RequestBody {
    RequestBody mRequestBody;
    private long mCurrentLength;
    private UploadListener mUploadListener;

    public ExRequestBody(RequestBody requestBody) {
        mRequestBody = requestBody;
    }

    public ExRequestBody(RequestBody mRequestBody, UploadListener uploadListener) {
        this.mRequestBody = mRequestBody;
        this.mUploadListener = uploadListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        final long contentLength = contentLength();
        ForwardingSink forwardingSink = new ForwardingSink(sink) {
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                mCurrentLength = byteCount + mCurrentLength;
                if (mUploadListener != null) {
                    mUploadListener.onProgress(contentLength, mCurrentLength);
                }
                super.write(source, byteCount);
            }
        };
        BufferedSink bufferedSink = Okio.buffer(forwardingSink);
        mRequestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }
}
