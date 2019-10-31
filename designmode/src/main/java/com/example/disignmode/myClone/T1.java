package com.example.disignmode.myClone;

import java.util.ArrayList;
import java.util.List;

/**
 * created by linghaoDo on 2019-10-31
 * description:
 * <p>
 * version:
 */
public class T1 implements Cloneable { // 1. 继承接口
    private String mText;
    private List<String> mImages = new ArrayList<>();

    @Override
    protected T1 clone() throws CloneNotSupportedException { // 重写clone
        try {
            T1 ti = (T1) super.clone();
            ti.mImages = this.mImages;
            ti.mText = this.mText;
            return ti;
        } catch (Exception e) {

        }
        return null;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public List<String> getmImages() {
        return mImages;
    }

    public void setmImages(List<String> mImages) {
        this.mImages = mImages;
    }

    public void addImages(String img) {
        this.mImages.add(img);
    }
    public void showDocument(){
        System.out.println("------Word Content Start ------");
        System.out.println(" Text: "+mText);
        System.out.println("Images List: ");
        for(String imgName :mImages){
            System.out.println("image name" + imgName);
        }
        System.out.println("-------- Word Content End --------");

    }


}
