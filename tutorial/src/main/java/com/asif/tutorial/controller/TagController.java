package com.asif.tutorial.controller;

import com.asif.tutorial.model.Tag;
import com.asif.tutorial.model.Tutorial;
import com.asif.tutorial.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> allTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/tutorials/{tutorialId}/tags")
    public ResponseEntity<List<Tag>> allTagsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        return tagService.getAllTagsByTutorialId(tutorialId);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> tagsById(@PathVariable(value = "id") Long id) {
        return tagService.getTagsById(id);
    }

    @GetMapping("/tags/{tagId}/tutorials")
    public ResponseEntity<List<Tutorial>> allTutorialsByTagId(@PathVariable(value = "tagId") Long tagId) {
        return tagService.getAllTutorialsByTagId(tagId);
    }

    @PostMapping("/tutorials/{tutorialId}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody Tag tagRequest) {
        return tagService.getAddedTag(tutorialId, tagRequest);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tagRequest) {
        return tagService.getUpdateTag(id, tagRequest);
    }

    @DeleteMapping("/tutorials/{tutorialId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "tutorialId") Long tutorialId, @PathVariable(value = "tagId") Long tagId) {
        return tagService.getDeletedTagFromTutorial(tutorialId, tagId);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
        return tagService.getDeleteTag(id);
    }
}
