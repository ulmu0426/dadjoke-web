package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.Comment;
import com.ulmu.dadjoketest.dto.CommentDto;
import com.ulmu.dadjoketest.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    @Test
    void createComment() {
        CommentDto comment = new CommentDto();
        comment.setCommentDetail("댓글 내용");
        comment.setCreateUserId(123L);
        comment.setJokeId(11L);

        CommentDto commentDto = commentService.createComment(comment);

        assertEquals("댓글 내용",commentDto.getCommentDetail());
        assertEquals(123L,commentDto.getCreateUserId());
        assertEquals(11L,commentDto.getJokeId());
        System.out.println(commentDto.getCommentId());
    }

    @Test
    void deleteComment() {
        Comment comment = new Comment();
        comment.setCommentDetail("삭제 내용");
        comment.setCreateUserId(123L);
        comment.setJokeId(11L);

        Comment savedComment = this.commentRepository.save(comment);

        CommentDto deleteCommentDto = commentService.getComment(savedComment.getCommentId());
        Long commentId = deleteCommentDto.getCommentId();
        commentService.deleteComment(commentId);

        Optional<Comment> res = commentRepository.findById(commentId);
        assertTrue(res.isEmpty());
    }

    @Test
    void putComment() {
    }

    @Test
    void greatComment() {
    }
}