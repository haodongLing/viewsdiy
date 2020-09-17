package com.haodong.structure2.structure.mytree;

import org.json.JSONArray;

/**
 * created by linghaoDo on 2020-03-04
 * description:
 * <p>
 * version:
 */
public class RedBlackTree {
    public static void main(String[] args) {

        System.out.println(new RedBlackTree().getHtmlFromData(null) + " ");
    }

    public String getHtmlFromData(String data) {
        try {
            String html = "";
            JSONArray jsonArray = new JSONArray(data);
            if (jsonArray.length() == 0) {
                return null;
            }
            return "ss";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
