package com.talentboost.ics.dto.datatosend;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ToSend {
    private String url;
    private Date added_on;
    private String service;
    private int width;
    private int height;
    private Set<ImageTag> imageTags = new HashSet<>();
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setService(String service) {
        this.service = service;
    }
    public String getService() {
        return service;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }

    public Date getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Date added_on) {
        this.added_on = added_on;
    }

    public Set<ImageTag> getImageTags() {
        return imageTags;
    }

    public void setImageTags(Set<ImageTag> imageTags) {
        this.imageTags = imageTags;
    }

    public void addImageTag(ImageTag imageTag) {
        this.imageTags.add(imageTag);
    }
}
