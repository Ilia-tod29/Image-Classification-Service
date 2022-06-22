package com.talentboost.ics.services;

import com.talentboost.ics.data.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ImageService {
    Image add(Image image);

    Image getImageById(Long imageId);

    Image getImageByUrl(String imageUrl);

    Page<Image> getPagedImages(Pageable page);

    List<Image> getAllImages();

    boolean existsByUrl(String url);

    boolean existsById(Long id);

    boolean existsByChecksum(Long checksum);
}
