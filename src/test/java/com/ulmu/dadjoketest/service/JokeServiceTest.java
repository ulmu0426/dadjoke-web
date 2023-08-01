package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.dto.JokeDto;
import com.ulmu.dadjoketest.repository.JokeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class JokeServiceTest {
    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    JokeService jokeService;

    @Test
    void createJoke() {
        JokeDto jokeDto = new JokeDto();
        jokeDto.setTitle("테스트 제목");
        jokeDto.setDetail("테스트 내용");
        jokeDto.setCreateUserId(123L);

        JokeDto newJokeDto = jokeService.createJoke(jokeDto);

        assertEquals("테스트 제목",newJokeDto.getTitle());
        assertEquals("테스트 내용",newJokeDto.getDetail());
        assertEquals(123L,newJokeDto.getCreateUserId());
        System.out.println(newJokeDto.getJokeId());
        System.out.println(newJokeDto.getUploadedAt());
    }

    @Test
    void getJoke() {
    }

    @Test
    void getJokeList() {
    }

    @Test
    void putJoke() {
    }

    @Test
    void deleteJoke() {
    }

    @Test
    void greatJoke() {
    }

}