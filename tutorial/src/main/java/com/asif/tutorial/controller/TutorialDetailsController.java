package com.asif.tutorial.controller;

import com.asif.tutorial.model.Tutorial;
import com.asif.tutorial.model.TutorialDetails;
import com.asif.tutorial.repository.TutorialDetailsRepository;
import com.asif.tutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialDetailsController {
    @Autowired
    private TutorialDetailsRepository detailsRepository;

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping("/tutorials/{id}/details")
    public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable(value = "id") Long id) {
        Optional<TutorialDetails> details = detailsRepository.findById(id);

        return new ResponseEntity<>(details.get(), HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") Long tutorialId,
                                                         @RequestBody TutorialDetails detailsRequest) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);

        detailsRequest.setCreatedOn(new java.util.Date());
        detailsRequest.setTutorial(tutorial.get());
        TutorialDetails details = detailsRepository.save(detailsRequest);

        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }

    @PutMapping("/details/{id}")
    public ResponseEntity<TutorialDetails> updateDetails(@PathVariable("id") long id,
                                                         @RequestBody TutorialDetails detailsRequest) {
        Optional<TutorialDetails> details = detailsRepository.findById(id);

        details.get().setCreatedBy(detailsRequest.getCreatedBy());

        return new ResponseEntity<>(detailsRepository.save(details.get()), HttpStatus.OK);
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteDetails(@PathVariable("id") long id) {
        detailsRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> deleteDetailsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        detailsRepository.deleteByTutorialId(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
