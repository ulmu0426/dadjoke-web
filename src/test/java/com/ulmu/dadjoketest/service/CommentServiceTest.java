package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.Comment;
import com.ulmu.dadjoketest.dto.CommentDto;
import com.ulmu.dadjoketest.mapper.CommentMapper;
import com.ulmu.dadjoketest.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
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
        Comment comment = new Comment();
        comment.setCommentDetail("수정 전 댓글");
        comment.setCreateUserId(123L);
        comment.setJokeId(11L);

        Comment savedComment = this.commentRepository.save(comment);
        CommentDto inputCommentDto = new CommentDto();
        inputCommentDto.setCommentDetail("수정 후 댓글");

        CommentDto changedCommentDto = this.commentService.putComment(savedComment.getCommentId(), inputCommentDto);

        assertEquals(changedCommentDto.getCommentDetail(), commentRepository.findById(savedComment.getCommentId()).get().getCommentDetail());
    }

    @Test
    void greatComment() {
        Comment comment = new Comment();
        comment.setCommentDetail("좋아요할 댓글");
        comment.setCreateUserId(123L);
        comment.setJokeId(11L);

        Comment savedComment = this.commentRepository.save(comment);

        commentService.greatComment(savedComment.getCommentId(), "204");
        commentService.greatComment(savedComment.getCommentId(), "205");
        commentService.greatComment(savedComment.getCommentId(), "206");
        CommentDto commentDto = CommentMapper.convertToDto(commentRepository.findById(savedComment.getCommentId()).get());

        assertEquals(3L, commentDto.getGreat());
    }

    @Test
    void getCommentList() {
        Comment comment1 = new Comment();
        comment1.setCommentDetail("11 유머글 댓글1");
        comment1.setCreateUserId(111L);
        comment1.setJokeId(11L);

        Comment comment2 = new Comment();
        comment2.setCommentDetail("11 유머글 댓글2");
        comment2.setCreateUserId(222L);
        comment2.setJokeId(11L);

        Comment comment3 = new Comment();
        comment3.setCommentDetail("11 유머글 댓글3");
        comment3.setCreateUserId(333L);
        comment3.setJokeId(11L);

        Comment comment4 = new Comment();
        comment4.setCommentDetail("11 유머글 댓글4");
        comment4.setCreateUserId(444L);
        comment4.setJokeId(11L);

        this.commentRepository.save(comment1);
        this.commentRepository.save(comment2);
        this.commentRepository.save(comment3);
        this.commentRepository.save(comment4);

        List<CommentDto> commentList = commentService.getCommentList(11L);

        assertEquals(4, commentList.size());
    }
}