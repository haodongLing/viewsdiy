package com.example.disignmode.diedaiqi;

import java.util.ArrayList;

/**
 * Author: tangyuan
 * Time : 2021/10/21
 * Description:
 */
public class PancakeHouseMenu {
    ArrayList menuItems;
    public PancakeHouseMenu(){
        menuItems=new ArrayList();

    }
    public void addItem(String name,String description,boolean vegetarian,double price){
        MenuItem menuItem=new MenuItem(name,description,vegetarian,price);
        menuItems.add(menuItem);
    }
    public ArrayList getMenuItems(){
        return  menuItems;
    }
}
