package com.ulmu.dadjoketest.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user", schema = "dadjoke", uniqueConstraints = {@UniqueConstraint(columnNames = "user_id")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private int userId;    //유저 고유 pk
    @Column(name = "name", unique = false, nullable = false)
    private String name;    //닉네임
    @Column(name = "email", unique = false, nullable = false)
    private String email;   //이메일
    @Column(name = "password", unique = false, nullable = false)
    private String password;    //비밀번호
    @Column(name = "created_at", unique = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;    //가입일
    @Column(name = "login_date", unique = false, nullable = true)
    @CreationTimestamp
    private Date loginDate;    //마지막 로그인날짜

    public User(int userId, String name, String email, String password, Date createdAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
