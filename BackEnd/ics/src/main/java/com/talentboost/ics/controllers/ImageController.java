package com.talentboost.ics.controllers;

import com.google.common.util.concurrent.RateLimiter;
import com.talentboost.ics.data.Image;
import com.talentboost.ics.services.ChecksumService;
import com.talentboost.ics.services.ImageService;
import com.talentboost.ics.services.ImaggaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;
    private final ImaggaService imaggaService;
    private final RateLimiter rateLimiter;
    private final ChecksumService checksumService;

    @Autowired
    public ImageController(ImageService imageService,
                           ImaggaService imaggaService,
                           RateLimiter rateLimiter,
                           ChecksumService checksumService) {
        this.imageService = imageService;
        this.imaggaService = imaggaService;
        this.rateLimiter = rateLimiter;
        this.checksumService = checksumService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<Page<Image>> getImages(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return new ResponseEntity<>(this.imageService.getPagedImages(page), HttpStatus.OK);
    }

    @GetMapping("/all-images")
    @CrossOrigin
    public ResponseEntity<List<Image>> getAllImages() {
        return new ResponseEntity<>(this.imageService.getAllImages(), HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    @CrossOrigin
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        if(!this.imageService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.imageService.getImageById(id), HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Image> addImageByUrl(@RequestBody String imageUrl) throws IOException {
        //Returning the image from db if it is already added
        Long checksum = this.checksumService.checksumURL(imageUrl);
        if(this.imageService.existsByUrl(imageUrl) || this.imageService.existsByChecksum(checksum)) {
            return new ResponseEntity<>(this.imageService.getImageByUrl(imageUrl), HttpStatus.OK);
        }

        //Adding the new image if it does not exist
        try{
            boolean canMakeAnRequest = this.rateLimiter.tryAcquire();
            if(canMakeAnRequest) {
                Image newImage = this.imaggaService.getImageData(imageUrl);
                Image addedImage = this.imageService.add(newImage);
                return new ResponseEntity<>(addedImage, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
            }
        } catch (IOException exception) {
            throw new IOException("The provided link is not an image!");
        }
    }

}













