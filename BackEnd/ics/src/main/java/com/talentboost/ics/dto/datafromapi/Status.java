package com.talentboost.ics.dto.datafromapi;

public class Status {
    private String text;
    private String type;
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
