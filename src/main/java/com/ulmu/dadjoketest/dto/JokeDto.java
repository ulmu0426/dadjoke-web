package com.ulmu.dadjoketest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JokeDto {
    private Long jokeId;

    private Long jokeNum;

    private String title;

    private String detail;

    private Date uploadedAt;

    private Long views;

    private Long createUserId;

    private List<Long> greatUserIdList;


    public Long getGreat() {
        return (long) greatUserIdList.size();
    }

}
