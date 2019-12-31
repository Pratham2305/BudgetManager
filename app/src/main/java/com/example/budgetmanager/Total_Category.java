package com.example.budgetmanager;

public class Total_Category {

    private String Category_name;
    private String Amount;

    public Total_Category(String category_name, String amount) {
        Category_name = category_name;
        Amount = amount;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public void setCategory_name(String category_name) {
        Category_name = category_name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
