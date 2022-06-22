package com.talentboost.ics.dto.datafromapi;

import java.util.List;

public class Result {
    private List<Tags> tags;
    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
    public List<Tags> getTags() {
        return tags;
    }
}
