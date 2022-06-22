package com.talentboost.ics.dto.datatosend;

public class ImageTag{
    private Tag tag;
    private double confidence;
    public void setTag(Tag tag) {
        this.tag = tag;
    }
    public Tag getTag() {
        return tag;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
    public double getConfidence() {
        return confidence;
    }
}
