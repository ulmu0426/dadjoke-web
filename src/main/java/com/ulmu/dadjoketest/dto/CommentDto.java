package com.ulmu.dadjoketest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CommentDto {
    private Long commentId;

    private String commentDetail;

    private Date createdAt;

    private Long jokeId;

    private Long createUserId;

    private List<Long> greatUserIdList;

    public Long getGreat() {
        return (long) this.greatUserIdList.size();
    }

}
