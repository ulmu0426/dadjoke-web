package com.ulmu.dadjoketest.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "joke", schema = "dadjoke", uniqueConstraints = {@UniqueConstraint(columnNames = "joke_id")})
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joke_id", unique = true, nullable = false)
    private Long jokeId;    //pk ID
    @Column(name = "joke_num", unique = false, nullable = false)
    private Long jokeNum;    //게시글 번호
    @Column(name = "title", unique = false, nullable = false)
    private String title;   //제목
    @Column(name = "detail", unique = false, nullable = false)
    private String detail;  //내용
    @Column(name = "uploaded_at", unique = false, nullable = false)
    @CreationTimestamp
    private Date uploadedAt;   //업로드시간
    @Column(name = "views", unique = false, nullable = false)
    private Long views;  //조회수
    @Column(name = "great", unique = false, nullable = false)
    private Long great;  //좋아요수
    @Column(name = "create_user_id", unique = false, nullable = false)
    private Long createUserId; //작성자
    @Column(name = "great_user_id_list", unique = false, nullable = false)
    private String greatUserIDList;     //좋아요 누른 유저 id list - 띄어쓰기로 구분해서 데이터 입력
                                        //DTO에서 Mapper가 List<String>을 ','로 구분되는 String으로 변환하여 통으로 저장하도록

    public Joke(Long jokeId, Long jokeNum, String title, String detail, Date uploadedAt, Long views, Long great, Long createUserId) {
        this.jokeId = jokeId;
        this.jokeNum = jokeNum;
        this.title = title;
        this.detail = detail;
        this.uploadedAt = uploadedAt;
        this.views = views;
        this.great = great;
        this.createUserId = createUserId;
    }

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
        return great;
    }

    public void setGreat(Long great) {
        this.great = great;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getGreatUserIDList() {
        return greatUserIDList;
    }

    public void setGreatUserIDList(String greatUserIDList) {
        this.greatUserIDList = greatUserIDList;
    }
}
