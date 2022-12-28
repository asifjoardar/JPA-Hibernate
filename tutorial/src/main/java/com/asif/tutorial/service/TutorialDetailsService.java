package com.asif.tutorial.service;

import com.asif.tutorial.model.Tutorial;
import com.asif.tutorial.model.TutorialDetails;
import com.asif.tutorial.repository.TutorialDetailsRepository;
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
public class TutorialDetailsService {
    @Autowired
    private TutorialDetailsRepository detailsRepository;

    @Autowired
    private TutorialRepository tutorialRepository;

    public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable(value = "id") Long id) {
        Optional<TutorialDetails> details = detailsRepository.findById(id);

        return new ResponseEntity<>(details.get(), HttpStatus.OK);
    }

    public ResponseEntity<TutorialDetails> getCreateDetails(@PathVariable(value = "tutorialId") Long tutorialId,
                                                         @RequestBody TutorialDetails detailsRequest) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);

        detailsRequest.setCreatedOn(new java.util.Date());
        detailsRequest.setTutorial(tutorial.get());
        TutorialDetails details = detailsRepository.save(detailsRequest);

        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }

    public ResponseEntity<TutorialDetails> getUpdateDetails(@PathVariable("id") long id,
                                                         @RequestBody TutorialDetails detailsRequest) {
        Optional<TutorialDetails> details = detailsRepository.findById(id);

        details.get().setCreatedBy(detailsRequest.getCreatedBy());

        return new ResponseEntity<>(detailsRepository.save(details.get()), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> getDeletedDetails(@PathVariable("id") long id) {
        detailsRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<TutorialDetails> getDeleteDetailsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        detailsRepository.deleteByTutorialId(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
