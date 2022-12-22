package com.asif.tutorial.repository;

import com.asif.tutorial.model.TutorialDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialDetailsRepository extends JpaRepository<TutorialDetails, Long> {
    @Transactional
    void deleteById(long id);

    @Transactional
    void deleteByTutorialId(long tutorialId);
}
