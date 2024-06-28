package com.hcorp.themesoflegends.repositopry;

import com.hcorp.themesoflegends.entity.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface HighScoreRepository extends JpaRepository<HighScore, Long> {
    List<HighScore> findAllByRoundNumberOrderByHighScoreValue(Long roundNumber);
}
