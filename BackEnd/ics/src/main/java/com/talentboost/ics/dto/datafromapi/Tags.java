package com.talentboost.ics.dto.datafromapi;

public class Tags {
    private Double confidence;
    private Tag tag;
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
    public Double getConfidence() {
        return confidence;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
    public Tag getTag() {
        return tag;
    }
}
