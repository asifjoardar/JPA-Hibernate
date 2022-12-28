package com.asif.tutorial.service;

import com.asif.tutorial.model.Tag;
import com.asif.tutorial.model.Tutorial;
import com.asif.tutorial.repository.TagRepository;
import com.asif.tutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private TagRepository tagRepository;

    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = new ArrayList<Tag>();
        tagRepository.findAll().forEach(tags::add);
        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    public ResponseEntity<List<Tag>> getAllTagsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        List<Tag> tags = tagRepository.findTagsByTutorialsId(tutorialId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    public ResponseEntity<Tag> getTagsById(@PathVariable(value = "id") Long id) {
        Optional<Tag> tag = tagRepository.findById(id);

        return new ResponseEntity<>(tag.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Tutorial>> getAllTutorialsByTagId(@PathVariable(value = "tagId") Long tagId) {

        List<Tutorial> tutorials = tutorialRepository.findTutorialsByTagsId(tagId);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    public ResponseEntity<Tag> getAddedTag(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody Tag tagRequest) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);

        long tagId = tagRequest.getId();

        // tag is existed
        if (tagId != 0L) {
            Optional<Tag> _tag = tagRepository.findById(tagId);
            tutorial.get().addTag(_tag.get());
            tutorialRepository.save(tutorial.get());
            return new ResponseEntity<>(_tag.get(), HttpStatus.CREATED);
        }

        // add and create new Tag
        tutorial.get().addTag(tagRequest);
        tagRepository.save(tagRequest);

        return new ResponseEntity<>(tagRequest, HttpStatus.CREATED);
    }

    public ResponseEntity<Tag> getUpdateTag(@PathVariable("id") long id, @RequestBody Tag tagRequest) {
        Optional<Tag> tag = tagRepository.findById(id);

        tag.get().setName(tagRequest.getName());

        return new ResponseEntity<>(tagRepository.save(tag.get()), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> getDeletedTagFromTutorial(@PathVariable(value = "tutorialId") Long tutorialId, @PathVariable(value = "tagId") Long tagId) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);

        tutorial.get().removeTag(tagId);
        tutorialRepository.save(tutorial.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> getDeleteTag(@PathVariable("id") long id) {
        tagRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
