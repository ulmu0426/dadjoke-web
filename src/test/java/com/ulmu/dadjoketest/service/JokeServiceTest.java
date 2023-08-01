package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.Joke;
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
        Joke joke = new Joke();
        joke.setTitle("Get 테스트 제목");
        joke.setDetail("Get 테스트 내용");
        joke.setCreateUserId(123L);

        Joke savedJoke = jokeRepository.save(joke);

        JokeDto resJoke = jokeService.getJoke(savedJoke.getJokeId());

        assertEquals(savedJoke.getTitle(), resJoke.getTitle());
        assertEquals(savedJoke.getDetail(), resJoke.getDetail());
        assertEquals(savedJoke.getCreateUserId(), resJoke.getCreateUserId());
    }

    @Test
    void getJokeList() {
    }

    @Test
    void putJoke() {
        Joke joke = new Joke();
        joke.setTitle("before changed title");
        joke.setDetail("before changed detail");
        joke.setCreateUserId(123L);
        joke.setGreat(10L);
        Joke savedJoke = jokeRepository.save(joke);
        JokeDto resJokeDto = new JokeDto();
        resJokeDto.setTitle("after changed title");
        resJokeDto.setDetail("after changed detail");
        JokeDto changedJoke = jokeService.putJoke(savedJoke.getJokeId(), resJokeDto);

        assertEquals(savedJoke.getTitle(), changedJoke.getTitle());
        assertEquals(savedJoke.getDetail(), changedJoke.getDetail());
    }

    @Test
    void deleteJoke() {
    }

    @Test
    void greatJoke() {
    }

}