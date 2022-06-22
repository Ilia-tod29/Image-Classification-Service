package com.talentboost.ics.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(catalog = "final_task", schema = "ics", name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url", unique = true)
    private String url;

    @Column(name = "added_on")
    private Date added_on;

    @Column(name = "service")
    private String service;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @JsonIgnore
    @Column(name = "checksum", unique = true)
    private Long checksum;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private Set<ImageTag> imageTags = new HashSet<>();

    public Image() {
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Date added_on) {
        this.added_on = added_on;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getWidth() { return width; }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Long getChecksum() { return checksum; }

    public void setChecksum(Long checksum) { this.checksum = checksum; }

    public Set<ImageTag> getImageTags() {
        return imageTags;
    }

    public void setImageTags(Set<ImageTag> imageTags) { this.imageTags = imageTags; }

    public void addImageTag(ImageTag imageTag) {
        this.imageTags.add(imageTag);
    }
}
