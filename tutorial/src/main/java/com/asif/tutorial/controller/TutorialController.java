package com.asif.tutorial.controller;


import com.asif.tutorial.model.Tutorial;
import com.asif.tutorial.repository.TutorialRepository;
import com.asif.tutorial.service.TutorialDetailsService;
import com.asif.tutorial.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> allTutorials(@RequestParam(required = false) String title) {
        return tutorialService.getAllTutorials(title);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> tutorialById(@PathVariable("id") long id) {
        return tutorialService.getTutorialById(id);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialService.getCreatedTutorial(tutorial);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        return tutorialService.getUpdatedTutorial(id, tutorial);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        return tutorialService.getDeletedTutorial(id);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        return tutorialService.getDeleteAllTutorials();
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        return tutorialService.getFindByPublished();
    }
}
