package com.ulmu.dadjoketest.dto;

import java.util.Date;
import java.util.List;

public class JokeDto {
    private Long jokeId;
    private Long jokeNum;
    private String title;
    private String detail;
    private Date uploadedAt;
    private Long views;
    private Long createUserId;
    private List<Long> greatUserIdList;

    public Long getJokeId() {
        return jokeId;
    }

    public void setJokeId(Long jokeId) {
        this.jokeId = jokeId;
    }

    public Long getJokeNum() {
        return jokeNum;
    }

    public void setJokeNum(Long jokeNum) {
        this.jokeNum = jokeNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getGreat() {
        return (long) greatUserIdList.size();
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
