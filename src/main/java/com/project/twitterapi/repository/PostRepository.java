package com.project.twitterapi.repository;

import com.project.twitterapi.domain.Post;
import com.project.twitterapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findByTimestampGreaterThan(Long timestamp);
}
