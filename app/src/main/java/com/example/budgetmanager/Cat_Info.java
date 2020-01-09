package com.example.budgetmanager;

public class Cat_Info {

    private  String Date;
    private String Category;
    private String Amount;

    public Cat_Info(String date, String category, String amount) {
        Date = date;
        Category = category;
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
