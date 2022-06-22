package com.talentboost.ics.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(catalog = "final_task", schema = "ics", name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ImageTag> imageTags = new HashSet<>();

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ImageTag> getImageTags() {
        return imageTags;
    }

    public void addImageTag(ImageTag imageTag) {
        this.imageTags.add(imageTag);
    }
}
