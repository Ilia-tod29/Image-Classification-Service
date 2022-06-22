package com.talentboost.ics.services.impl;

import com.talentboost.ics.services.ChecksumService;
import com.talentboost.ics.services.ImaggaService;
import com.talentboost.ics.services.TagService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class ImaggaServiceImplTest {

    private ImaggaService imaggaService;
    private TagService tagService;
    private ChecksumService checksumService;

    @BeforeEach
    void setUp() {
        this.tagService = mock(TagService.class);
        this.checksumService = mock(ChecksumService.class);
        this.imaggaService = new ImaggaServiceImpl(this.tagService, this.checksumService);
    }

    @Test
    void getImageData() {
        Assertions.assertThrows(IOException.class, () -> this.imaggaService.getImageData("Any string"));
        Mockito.verify(this.tagService, never()).existsTagByName(ArgumentMatchers.anyString());
        Mockito.verify(this.tagService, never()).getTagByName(ArgumentMatchers.anyString());
        Mockito.verify(this.checksumService, never()).checksumURL(ArgumentMatchers.anyString());
    }
}