package com.project.twitterapi.repository;

import com.project.twitterapi.domain.Follow;
import com.project.twitterapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByUserAndFollowing(User user, User following);

    List<Follow> findByUser(User user);
}
