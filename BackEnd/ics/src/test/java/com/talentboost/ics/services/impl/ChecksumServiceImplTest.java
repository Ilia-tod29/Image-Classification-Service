package com.talentboost.ics.services.impl;

import com.talentboost.ics.services.ChecksumService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ChecksumServiceImplTest {

    private ChecksumService checksumService;

    @BeforeEach
    void setUp() {
        this.checksumService = new ChecksumServiceImpl();
    }

    @Test
    void testWorkingChecksumURLMethod() {
        //This will be valid while the link is valid online
        String url = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg"; //Random image from db
        assertEquals(1068531961, this.checksumService.checksumURL(url));
    }

    @Test
    void testInvalidLinkInChecksumURLMethod() {
        Assertions.assertThrows(RuntimeException.class, () -> this.checksumService.checksumURL("Any string that is not an url."));
    }
}