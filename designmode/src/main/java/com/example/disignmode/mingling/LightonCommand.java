package com.example.disignmode.mingling;

/**
 * Author: tangyuan
 * Time : 2021/10/14
 * Description:
 */
public class LightonCommand implements Command {
    private Light mLight;

    public LightonCommand(Light light) {
        this.mLight = light;
    }

    /**
     * 真正执行
     */
    @Override
    public void execute() {
        mLight.on();
    }
}
