package com.ulmu.dadjoketest.repository;

import com.ulmu.dadjoketest.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByJokeIdOrderByCommentId(Long jokeId);
}
