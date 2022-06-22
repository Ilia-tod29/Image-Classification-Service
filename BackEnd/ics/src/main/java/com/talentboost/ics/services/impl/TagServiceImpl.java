package com.talentboost.ics.services.impl;

import com.talentboost.ics.data.Tag;
import com.talentboost.ics.repositories.TagRepository;
import com.talentboost.ics.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag add(Tag tag) {
        return this.tagRepository.save(tag);
    }

    @Override
    public Tag get(Long tagId) {
        Optional<Tag> tag = this.tagRepository.findById(tagId);
        if(tag.isPresent()) {
            return tag.get();
        }
        throw new NoSuchElementException("Tag with ID: " + tagId + " was not found!");
    }

    @Override
    public Tag getTagByName(String name) {
        return this.tagRepository.getTagByName(name);
    }

    @Override
    public boolean existsTagByName(String name) {
        return this.tagRepository.existsTagByName(name);
    }

    @Override
    public List<Tag> getAllTags() {
        return this.tagRepository.findAll();
    }


}
