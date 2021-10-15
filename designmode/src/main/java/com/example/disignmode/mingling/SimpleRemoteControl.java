package com.example.disignmode.mingling;

/**
 * Author: tangyuan
 * Time : 2021/10/14
 * Description:
 */
public class SimpleRemoteControl {
    Command slot;


    public void setCommand(Command slot) {
        this.slot = slot;
    }
    public void buttonWasPressed(){
        if (slot!=null){
            slot.execute();
        }
    }


}
