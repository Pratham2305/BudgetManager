package com.example.budgetmanager;

public class Income {

    private String itemname;
    private String category;


    public Income(String itemname, String category) {
        this.itemname = itemname;
        this.category = category;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }




}
