package com.example.disignmode.myClone;

/**
 * created by linghaoDo on 2019-10-31
 * description:
 * <p>
 * version:
 */
public class MainT1 {
    public static void main(String[] args) {
        T1 origin = new T1();
        origin.setmText("这是一篇文档");
        origin.addImages("图片1");
        origin.addImages("图片2");
        origin.addImages("图片3");

        try {
            T1 t2 = origin.clone();
            t2.showDocument();
            // 修改文字和图片

            t2.setmText("这是修改后的Doc2 文本");
            t2.addImages("哈哈.png");
            t2.showDocument();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

    public static class Client {
        public static void main(String[] args) {
            T1 origin = new T1();
            origin.setmText("这是一篇文档");
            origin.addImages("图片1");
            origin.addImages("图片2");
            origin.addImages("图片3");
            origin.showDocument();
            try {
                T1 now = origin.clone();
                now.setmText("这是修改之后的doc文档");
                now.addImages("新增的图片");
                now.showDocument();
                origin.showDocument();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }
    }

}
