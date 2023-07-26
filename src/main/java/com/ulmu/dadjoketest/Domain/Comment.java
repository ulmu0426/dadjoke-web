package com.ulmu.dadjoketest.Domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comment", schema = "dadjoke", uniqueConstraints = {@UniqueConstraint(columnNames = "comment_id")})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private int commentId; //pk
    @Column(name = "comment_detail", unique = false, nullable = false)
    private String commentDetail;  //댓글내용
    @Column(name = "created_at", unique = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;    //댓글 작성일
    @Column(name = "great", unique = false, nullable = false)
    private int great;  //댓글 좋아요 수
    @Column(name = "joke_id", unique = true, nullable = false)
    private int jokeId; //댓글을 단 농담 id
    @Column(name = "create_user_id", unique = true, nullable = false)
    private int createUserUd; //댓글 작성자 id
    @Column(name = "comment_great_user_list", unique = false, nullable = false)
    private String greatUserIDList;     //좋아요 누른 유저 id list - 띄어쓰기로 구분해서 데이터 입력
                                        //DTO에서 Mapper가 List<String>을 ','로 구분되는 String으로 변환하여 통으로 저장하도록

    public Comment(int commentId, String commentDetail, Date createdAt, int great, int jokeId, int createUserUd) {
        this.commentId = commentId;
        this.commentDetail = commentDetail;
        this.createdAt = createdAt;
        this.great = great;
        this.jokeId = jokeId;
        this.createUserUd = createUserUd;
    }


}
