package com.asif.tutorial.controller;

import com.asif.tutorial.model.Comment;
import com.asif.tutorial.model.Tutorial;
import com.asif.tutorial.repository.CommentRepository;
import com.asif.tutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        List<Comment> comments = commentRepository.findByTutorialId(tutorialId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
        Optional<Comment> comment = commentRepository.findById(id);

        return new ResponseEntity<>(comment.get(), HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                 @RequestBody Comment commentRequest) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);
        commentRequest.setTutorial(tutorial.get());
        commentRepository.save(commentRequest);

        return new ResponseEntity<>(commentRequest, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
        Optional<Comment> comment = commentRepository.findById(id);

        comment.get().setContent(commentRequest.getContent());

        return new ResponseEntity<>(commentRepository.save(comment.get()), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        commentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        commentRepository.deleteByTutorialId(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
