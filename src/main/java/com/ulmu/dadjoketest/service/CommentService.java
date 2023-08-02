package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.Comment;
import com.ulmu.dadjoketest.dto.CommentDto;
import com.ulmu.dadjoketest.mapper.CommentMapper;
import com.ulmu.dadjoketest.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentDto createComment(CommentDto commentDto){
        Comment res = CommentMapper.convertToModel(commentDto);
        this.commentRepository.save(res);
        return CommentMapper.convertToDto(res);
    }

    public void deleteComment(Long commentId){
        Optional<Comment> deleteComment = this.commentRepository.findById(commentId);
        if (deleteComment.isEmpty()){
            throw new NoSuchElementException(String.format("해당 댓글을 찾을 수 없습니다. ID : %s", commentId));
        }
        this.commentRepository.delete(deleteComment.get());
    }

    public CommentDto putComment(Long commentId, CommentDto commentDto){
        Optional<Comment> res = commentRepository.findById(commentId);
        if(res.isEmpty()){
            throw new NoSuchElementException(String.format("해당 댓글 %s가 존재하지 않습니다.", commentId));
        }
        Comment updateComment = res.get();
        updateComment.setCommentDetail(commentDto.getCommentDetail());
        Comment savedComment = this.commentRepository.save(updateComment);

        return CommentMapper.convertToDto(savedComment);
    }

    public CommentDto greatComment(Long commentId, String greatUserId){
        Optional<Comment> res = commentRepository.findById(commentId);
        if (res.isEmpty()){
            throw new NoSuchElementException(String.format("해당 댓글 %s가 존재하지 않습니다.", commentId));
        }
        Comment updateGreatUserListInComment = res.get();
        updateGreatUserListInComment.setGreatUserIdList(greatUserId);
        Comment savedComment = this.commentRepository.save(updateGreatUserListInComment);

        return CommentMapper.convertToDto(savedComment);
    }

    public CommentDto getComment(Long commentId) {
        Optional<Comment> res = commentRepository.findById(commentId);
        if (res.isEmpty()){
            throw new NoSuchElementException(String.format("해당 댓글 %s가 존재하지 않습니다.", commentId));
        }else {
            return CommentMapper.convertToDto(res.get());
        }
    }
}
