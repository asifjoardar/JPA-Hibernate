package com.asif.tutorial.service;

import com.asif.tutorial.model.Comment;
import com.asif.tutorial.repository.CommentRepository;
import com.asif.tutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllCommentsByTutorialId(Long tutorialId) {
        return commentRepository.findByTutorialId(tutorialId);
    }
    public Comment getCommentsByTutorialId(Long id) {
        return commentRepository.findById(id).get();
    }
    public Comment getCreatedComment(Long tutorialId,Comment commentRequest) {
        commentRequest.setTutorial(tutorialRepository.findById(tutorialId).get());
        commentRepository.save(commentRequest);
        return commentRequest;
    }
    public Comment getUpdatedComment(Long id,Comment commentRequest) {
        Optional<Comment> comment = commentRepository.findById(id);
        comment.get().setContent(commentRequest.getContent());
        return commentRepository.save(comment.get());
    }
    public HttpStatus getDeleteComment(Long id) {
        commentRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
    public HttpStatus getDeleteAllCommentsOfTutorial(Long tutorialId) {
        commentRepository.deleteByTutorialId(tutorialId);
        return HttpStatus.NO_CONTENT;
    }
}
