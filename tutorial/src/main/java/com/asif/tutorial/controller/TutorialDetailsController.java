package com.asif.tutorial.controller;

import com.asif.tutorial.model.Tutorial;
import com.asif.tutorial.model.TutorialDetails;
import com.asif.tutorial.repository.TutorialDetailsRepository;
import com.asif.tutorial.repository.TutorialRepository;
import com.asif.tutorial.service.TutorialDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialDetailsController {
    @Autowired
    private TutorialDetailsService tutorialDetailsService;

    @GetMapping("/tutorials/{id}/details")
    public ResponseEntity<TutorialDetails> detailsById(@PathVariable(value = "id") Long id) {
        return tutorialDetailsService.getDetailsById(id);
    }

    @PostMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") Long tutorialId,
                                                         @RequestBody TutorialDetails detailsRequest) {
        return tutorialDetailsService.getCreateDetails(tutorialId, detailsRequest);
    }

    @PutMapping("/details/{id}")
    public ResponseEntity<TutorialDetails> updateDetails(@PathVariable("id") long id,
                                                         @RequestBody TutorialDetails detailsRequest) {
        return tutorialDetailsService.getUpdateDetails(id, detailsRequest);
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteDetails(@PathVariable("id") long id) {
        return tutorialDetailsService.getDeletedDetails(id);
    }

    @DeleteMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> deleteDetailsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        return tutorialDetailsService.getDeleteDetailsOfTutorial(tutorialId);
    }
}
