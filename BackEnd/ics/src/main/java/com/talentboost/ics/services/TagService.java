package com.talentboost.ics.services;

import com.talentboost.ics.data.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TagService {
    Tag add(Tag tag);

    Tag get(Long tagId);

    Tag getTagByName(String name);

    boolean existsTagByName(String name);

    List<Tag> getAllTags();
}
