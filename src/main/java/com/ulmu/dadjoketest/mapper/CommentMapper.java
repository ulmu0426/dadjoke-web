package com.ulmu.dadjoketest.mapper;

import com.ulmu.dadjoketest.domain.Comment;
import com.ulmu.dadjoketest.dto.CommentDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommentMapper {
    public static CommentDto convertToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setCommentDetail(comment.getCommentDetail());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setJokeId(comment.getJokeId());
        commentDto.setCreateUserId(comment.getCreateUserId());
        if (!comment.getGreatUserIdList().isEmpty()) {
            commentDto.setGreatUserIdList(convertToLongList(comment.getGreatUserIdList()));
        }
        return commentDto;
    }
    private static List<Long> convertToLongList(String greatUserIdList){
        List<Long> convertedList = Stream.of(greatUserIdList.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return convertedList;
    }

    public static Comment convertToModel(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setCommentId(commentDto.getCommentId());
        comment.setCommentDetail(commentDto.getCommentDetail());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setJokeId(commentDto.getJokeId());
        comment.setCreateUserId(commentDto.getCreateUserId());
        if (commentDto.getGreatUserIdList() != null) {
            comment.setGreatUserIdList(convertToLongList(commentDto.getGreatUserIdList()));
        }
        return comment;
    }
    private static String convertToLongList(List<Long> greatUserIdList){
        String convertedList = greatUserIdList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(","));
        return convertedList;
    }

    public static List<CommentDto> convertToDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(CommentMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
