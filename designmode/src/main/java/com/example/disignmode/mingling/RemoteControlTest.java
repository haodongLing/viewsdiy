package com.example.disignmode.mingling;

/**
 * Author: tangyuan
 * Time : 2021/10/14
 * Description:
 */
public class RemoteControlTest {
    public static void main(String [] args){
        SimpleRemoteControl remoteControl=new SimpleRemoteControl();
        Light light=new Light();
        LightonCommand lightonCommand=new LightonCommand(light);
        remoteControl.setCommand(lightonCommand);
        remoteControl.buttonWasPressed();
    }
}
