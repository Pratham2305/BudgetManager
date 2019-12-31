package com.example.budgetmanager;

public class Total_Category01 {

    private String Category_Name;
    private String Amount;

    public Total_Category01(String category_name, String amount) {
        Category_Name = category_name;
        Amount = amount;
    }

    public String getCategory_name() {
        return Category_Name;
    }

    public void setCategory_name(String category_name) {
        Category_Name = category_name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
