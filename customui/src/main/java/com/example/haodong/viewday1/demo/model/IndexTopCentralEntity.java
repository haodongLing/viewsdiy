package com.example.haodong.viewday1.demo.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class IndexTopCentralEntity implements Serializable {
    //index和hot 相同字段
    @Expose
    public String title;
    @Expose
    public String subTitle;
    @Expose
    public String icon;
    @Expose
    public String url;
    @Expose
    public String badge;


    //热卖不同字段
    @Expose
    public String tag;
    @Expose
    public String name;
    @Expose
    public String corner;

    public int position= -1;
}
