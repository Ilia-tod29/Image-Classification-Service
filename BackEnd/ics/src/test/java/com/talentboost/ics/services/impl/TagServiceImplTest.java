package com.talentboost.ics.services.impl;

import com.talentboost.ics.data.Tag;
import com.talentboost.ics.repositories.TagRepository;
import com.talentboost.ics.services.TagService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TagServiceImplTest {
    private TagRepository tagRepository;
    private Tag tag;
    private TagService tagService;

    @BeforeEach
    void setUp() {
        this.tagRepository = mock(TagRepository.class);
        this.tagService = new TagServiceImpl(this.tagRepository);

        this.tag = new Tag();
        this.tag.setId(53L);
        this.tag.setName("forest");
    }


    @Test
    void testWhenAddMethodWorks() {
        Tag customTag = new Tag();
        customTag.setId(1L);
        customTag.setName("sunrise");

        when(this.tagRepository.save(this.tag)).thenReturn(customTag);

        assertEquals("sunrise", this.tagRepository.save(this.tag).getName());
        assertEquals(1L, this.tagRepository.save(this.tag).getId());
        assertEquals(customTag, this.tagRepository.save(this.tag));
    }

    @Test
    void testWorkingGetTagByIdMethod() {
        when(this.tagRepository.getById(this.tag.getId())).thenReturn(this.tag);

        assertEquals("forest", this.tagRepository.getById(this.tag.getId()).getName());
        assertEquals(53L, this.tagRepository.getById(this.tag.getId()).getId());
        assertEquals(this.tag, this.tagRepository.getById(this.tag.getId()));
    }

    @Test
    void testWorkingGetTagByNameMethod() {
        when(this.tagRepository.getTagByName(this.tag.getName())).thenReturn(this.tag);

        assertEquals("forest", this.tagRepository.getTagByName(this.tag.getName()).getName());
        assertEquals(53L, this.tagRepository.getTagByName(this.tag.getName()).getId());
        assertEquals(this.tag, this.tagRepository.getTagByName(this.tag.getName()));
    }

    @Test
    void existsTagByName() {
        when(this.tagRepository.existsTagByName(this.tag.getName())).thenReturn(true);

        assertTrue(this.tagService.existsTagByName("forest"));
    }

    @Test
    void testWorkingGetMethod() {
        when(this.tagRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> this.tagService.get(1L));

        verify(this.tagRepository, times(1)).findById(ArgumentMatchers.anyLong());
    }
}