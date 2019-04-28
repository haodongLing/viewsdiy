package com.example.disignmode.myeventbus;

/**
 * describe :
 * date on 2019/4/28
 * author linghailong
 * email 105354999@qq.com
 */
public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
