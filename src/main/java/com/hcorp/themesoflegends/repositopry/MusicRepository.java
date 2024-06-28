package com.hcorp.themesoflegends.repositopry;

import com.hcorp.themesoflegends.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByToken(String token);
}
