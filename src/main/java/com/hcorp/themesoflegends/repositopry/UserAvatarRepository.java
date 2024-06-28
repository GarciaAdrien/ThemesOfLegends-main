package com.hcorp.themesoflegends.repositopry;

import com.hcorp.themesoflegends.entity.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface UserAvatarRepository extends JpaRepository<UserAvatar, Long> {
    List<UserAvatar> findAllByUserId(Long id);
    @Query("SELECT ua FROM UserAvatar ua WHERE ua.avatar.id = :avatarId AND ua.user.id = :userId")
    UserAvatar findByUserIdAndAvatarId(@Param("userId") Long userId, @Param("avatarId") Long avatarId);
    @Query("SELECT ua FROM UserAvatar ua WHERE ua.user.id = :userId AND ua.isSelected = true")
    UserAvatar findByUserIdAndSelectableIsTrue(@Param("userId") Long userId);
}
