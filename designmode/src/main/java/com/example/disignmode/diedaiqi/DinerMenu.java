package com.example.disignmode.diedaiqi;

/**
 * Author: tangyuan
 * Time : 2021/10/21
 * Description:
 */
public class DinerMenu {
    static final int Max_items=6;
    int numberOfItems=0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems=new MenuItem[Max_items];

    }
    public void addItems(String name,String description,boolean vegetarian,double price){
        MenuItem menuItem=new MenuItem(name, description, vegetarian, price);
        if (numberOfItems>=Max_items){
            System.out.println("Sorry,menu is full ! Can't add item to menu");
        }
        else
        {
            menuItems[numberOfItems]=menuItem;
            numberOfItems=numberOfItems+1;
        }
    }
    public MenuItem[] getMenuItems(){
        return menuItems;
    }
}
