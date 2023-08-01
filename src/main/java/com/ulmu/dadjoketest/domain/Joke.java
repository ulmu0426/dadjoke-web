package com.ulmu.dadjoketest.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "joke", schema = "dadjoke", uniqueConstraints = {@UniqueConstraint(columnNames = "joke_id")})
@Getter
@Setter
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joke_id", unique = true, nullable = false)
    private Long jokeId;    //pk ID

    @Column(name = "joke_num", unique = false, nullable = true)
    private Long jokeNum;    //게시글 번호

    @Column(name = "title", unique = false, nullable = false)
    private String title;   //제목

    @Column(name = "detail", unique = false, nullable = false)
    private String detail;  //내용

    @Column(name = "great", unique = false, nullable = false)
    private Long great;     //좋아요 수

    @Column(name = "uploaded_at", unique = false, nullable = true)
    @CreationTimestamp
    private Date uploadedAt;   //업로드시간

    @Column(name = "views", unique = false, nullable = true)
    private Long views;  //조회수

    @Column(name = "create_user_id", unique = false, nullable = false)
    private Long createUserId; //작성자

    @Column(name = "great_user_id_list", unique = false, nullable = false)
    private String greatUserIDList;     //좋아요 누른 유저 id list - 띄어쓰기로 구분해서 데이터 입력
                                        //DTO에서 Mapper가 List<String>을 ','로 구분되는 String으로 변환하여 통으로 저장하도록

    public Joke() {
        this.great = 0L;
        this.views = 0L;
        this.greatUserIDList = "";
    }

    public void setGreatUserIdList(String greatUserIDList) {
        if(this.greatUserIDList.isEmpty()){
            this.greatUserIDList = greatUserIDList;
        }else {
            this.greatUserIDList += "," + greatUserIDList;
        }
    }
}
