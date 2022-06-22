package com.talentboost.ics.services.impl;

import com.talentboost.ics.data.Image;
import com.talentboost.ics.data.ImageTag;
import com.talentboost.ics.data.Tag;
import com.talentboost.ics.repositories.ImageRepository;
import com.talentboost.ics.services.ImageService;
import com.talentboost.ics.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {


    private final ImageRepository imageRepository;
    private final TagService tagService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, TagService tagService) {
        this.imageRepository = imageRepository;
        this.tagService = tagService;
    }

    @Override
    public Image add(Image image) {
        //Creating a new image
        Image newImage = new Image();

        //Setting the parameters from the given one
        newImage.setUrl(image.getUrl());
        newImage.setAdded_on(image.getAdded_on());
        newImage.setService(image.getService());
        newImage.setWidth(image.getWidth());
        newImage.setHeight(image.getHeight());
        newImage.setChecksum(image.getChecksum());

        //Mapping the connection between the image and it's tags
        newImage.getImageTags().addAll((image.getImageTags()
                .stream()
                .map(imageTag -> {
                    Tag tag = tagService.get(imageTag.getTag().getId());
                    if(imageTag.getConfidence() > 30) {
                        ImageTag newImageTag = new ImageTag();
                        newImageTag.setTag(tag);
                        newImageTag.setImage(newImage);
                        newImageTag.setConfidence(imageTag.getConfidence());
                        return newImageTag;
                    }
                    return null;
                })
                .collect(Collectors.toSet())));

        //Save the new image
        return this.imageRepository.save(newImage);
    }

    @Override
    public Image getImageById(Long imageId) {
        Optional<Image> image = this.imageRepository.findById(imageId);
        if(image.isPresent()) {
            return image.get();
        }
        throw new NoSuchElementException("Image with ID: " + imageId + " was not found!");
    }

    @Override
    public Image getImageByUrl(String imageUrl) {
        return this.imageRepository.getImageByUrl(imageUrl);
    }


    @Override
    public List<Image> getAllImages() {return this.imageRepository.findAll(); }

    @Override
    public Page<Image> getPagedImages(Pageable page) {
        return this.imageRepository.findAll(page);
    }


    @Override
    public boolean existsByUrl(String url) {
        return this.imageRepository.existsByUrl(url);
    }

    @Override
    public boolean existsById(Long id) {
        return this.imageRepository.existsById(id);
    }

    @Override
    public boolean existsByChecksum(Long checksum) {
        return this.imageRepository.existsByChecksum(checksum);
    }


}
