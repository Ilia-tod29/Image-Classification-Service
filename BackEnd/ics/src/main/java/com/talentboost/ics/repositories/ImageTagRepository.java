package com.talentboost.ics.repositories;

import com.talentboost.ics.data.ImageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageTagRepository extends JpaRepository<ImageTag, ImageTag.ImageTagId> {
    List<ImageTag> getImageTagByImageId(Long id);
    List<ImageTag> getImageTagByTagId(Long id);
}
