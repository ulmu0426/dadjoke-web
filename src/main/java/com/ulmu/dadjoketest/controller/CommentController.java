package com.ulmu.dadjoketest.controller;

import com.ulmu.dadjoketest.dto.CommentDto;
import com.ulmu.dadjoketest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/joke/{jokeId}/comment/{commentId}")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentDto> createComment(@RequestBody final CommentDto commentDto){
        CommentDto newComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @GetMapping("/getList")
    public ResponseEntity<List<CommentDto>> getCommentList(@PathVariable("jokeId") final Long jokeId){
        //개그에 달린 댓글 목록 요청 응답
        List<CommentDto> commentDtoList = commentService.getCommentList(jokeId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @PutMapping("/put")
    public ResponseEntity<CommentDto> putComment(@PathVariable("commentId") final Long commentId,
                                                 @RequestBody final CommentDto commentDto){
        CommentDto changedCommentDto = commentService.putComment(commentId, commentDto);
        return new ResponseEntity<>(changedCommentDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") final Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/great")
    public ResponseEntity<CommentDto> greatComment(@PathVariable("commentId") final Long commentId,
                                                   @RequestBody final String greatUserId){
        CommentDto commentDto = commentService.greatComment(commentId, greatUserId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CommentDto> getComment(@PathVariable("commentId") final Long commentId){
        CommentDto commentDto = commentService.getComment(commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
}
