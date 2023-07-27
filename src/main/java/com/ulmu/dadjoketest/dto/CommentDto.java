package com.ulmu.dadjoketest.dto;

import java.util.Date;
import java.util.List;

public class CommentDto {
    private Long commentId;
    private String commentDetail;
    private Date createdAt;
    private Long great;
    private Long jokeId;
    private Long createUserId;
    private List<Long> greatUserIdList;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getGreat() {
        return great;
    }

    public void setGreat(Long great) {
        this.great = great;
    }

    public Long getJokeId() {
        return jokeId;
    }

    public void setJokeId(Long jokeId) {
        this.jokeId = jokeId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public List<Long> getGreatUserIdList() {
        return greatUserIdList;
    }

    public void setGreatUserIdList(List<Long> greatUserIdList) {
        this.greatUserIdList = greatUserIdList;
    }
}
