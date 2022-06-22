package com.talentboost.ics.controllers;

import com.talentboost.ics.data.Tag;
import com.talentboost.ics.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    TagController(){}

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Tag>> getTags() {
        return new ResponseEntity<>(this.tagService.getAllTags(), HttpStatus.OK);
    }

}
