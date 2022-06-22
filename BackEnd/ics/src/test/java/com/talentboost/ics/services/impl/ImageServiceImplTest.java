package com.talentboost.ics.services.impl;

import com.talentboost.ics.data.Image;
import com.talentboost.ics.repositories.ImageRepository;
import com.talentboost.ics.services.ImageService;
import com.talentboost.ics.services.TagService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ImageServiceImplTest {

    private Image image;
    private ImageRepository imageRepository;
    private TagService tagService;
    private ImageService imageService;

    @BeforeEach
    void setUp() {
        this.imageRepository = mock(ImageRepository.class);
        this.tagService = mock(TagService.class);
        this.imageService = new ImageServiceImpl(this.imageRepository, this.tagService);

        this.image = new Image();
        this.image.setUrl("https://someImage.jpeg");
        this.image.setId(5L);
        this.image.setChecksum(5236469756L);
        this.image.setService("Imagga");
    }

    @Test
    void testWorkingGetImageByIdMethod() {
        when(this.imageRepository.findById(5L)).thenReturn(Optional.ofNullable(this.image));

        assertEquals(this.imageService.getImageById(5L).getUrl(), "https://someImage.jpeg");
        assertEquals(this.imageService.getImageById(5L).getId(), 5L);
        assertEquals(this.imageService.getImageById(5L).getService(), "Imagga");
    }

    @Test
    void testInvalidGetImageByIdMethod() {
        when(this.imageRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> this.imageService.getImageById(1L));

        verify(this.imageRepository, times(1)).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void testWorkingGetImageByUrlMethod() {
        when(this.imageRepository.getImageByUrl("https://someImage.jpeg")).thenReturn(this.image);

        assertEquals(this.imageService.getImageByUrl("https://someImage.jpeg").getUrl(), "https://someImage.jpeg");
        assertEquals(this.imageService.getImageByUrl("https://someImage.jpeg").getId(), 5L);
        assertEquals(this.imageService.getImageByUrl("https://someImage.jpeg").getService(), "Imagga");
    }

    @Test
    void testWorkingGetAllImagesMethod() {
        Page<Image> mockPage = mock(Page.class);
        Pageable toPass = mock(Pageable.class);
        when(this.imageRepository.findAll(toPass)).thenReturn(mockPage);

        assertEquals(mockPage, this.imageService.getPagedImages(toPass));
    }

    @Test
    void testWorkingExistsByUrlMethod() {
        when(this.imageRepository.existsByUrl(this.image.getUrl())).thenReturn(true);

        assertTrue(this.imageService.existsByUrl("https://someImage.jpeg"));

        verify(this.imageRepository, times(1)).existsByUrl(ArgumentMatchers.anyString());
    }

    @Test
    void testWorkingExistsByIdMethod() {
        when(this.imageRepository.existsById(this.image.getId())).thenReturn(true);

        assertTrue(this.imageService.existsById(5L));

        verify(this.imageRepository, times(1)).existsById(ArgumentMatchers.anyLong());
    }

    @Test
    void testMethodExistsByChecksumMethod() {
        when(this.imageRepository.existsByChecksum(this.image.getChecksum())).thenReturn(true);

        assertTrue(this.imageService.existsByChecksum(5236469756L));

        verify(this.imageRepository, times(1)).existsByChecksum(ArgumentMatchers.anyLong());
    }
}