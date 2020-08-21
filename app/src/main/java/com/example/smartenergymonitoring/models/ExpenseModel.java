package com.example.smartenergymonitoring.models;

import java.io.Serializable;

public class ExpenseModel implements Serializable {
    private String id;
    private String title;
    private String desc;
    private String amount;
    private String timeStamp;

    public ExpenseModel() {
    }

    public ExpenseModel(String id, String title, String desc, String amount, String timeStamp) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
