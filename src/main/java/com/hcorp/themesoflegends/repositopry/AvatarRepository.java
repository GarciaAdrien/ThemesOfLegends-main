package com.hcorp.themesoflegends.repositopry;

import com.hcorp.themesoflegends.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

}
