package com.talentboost.ics.dto;

import java.util.Date;
import java.util.Set;

public class ImageDto {
    public String url;
    public Date added_on;
    public String service;
    public int width;
    public int height;
    public Set<ImageTagDto> imageTags;
}
