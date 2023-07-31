package com.ulmu.dadjoketest.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user", schema = "dadjoke", uniqueConstraints = {@UniqueConstraint(columnNames = "user_id")})
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;    //유저 고유 pk

    @Column(name = "name", unique = true, nullable = false)
    private String name;    //닉네임

    @Column(name = "email", unique = true, nullable = false)
    private String email;   //이메일

    @Column(name = "password", unique = false, nullable = false)
    private String password;    //비밀번호

    @Column(name = "created_at", unique = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;    //가입일

    @Column(name = "login_date", unique = false, nullable = true)
    @CreationTimestamp
    private Date loginDate;    //마지막 로그인날짜

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return String.valueOf(name);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
