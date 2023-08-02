package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.Joke;
import com.ulmu.dadjoketest.dto.JokeDto;
import com.ulmu.dadjoketest.mapper.JokeMapper;
import com.ulmu.dadjoketest.repository.JokeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class JokeServiceTest {
    @Autowired
    JokeRepository jokeRepository;

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
        Joke joke1 = new Joke();
        joke1.setTitle("joke2");
        joke1.setDetail("Get 테스트 내용");
        joke1.setCreateUserId(123L);
        joke1.setGreat(10L);
        jokeRepository.save(joke1);

        Joke joke2 = new Joke();
        joke2.setTitle("joke2");
        joke2.setDetail("Get 테스트 내용");
        joke2.setCreateUserId(123L);
        joke2.setGreat(5L);
        jokeRepository.save(joke2);

        Joke joke3 = new Joke();
        joke3.setTitle("joke3");
        joke3.setDetail("Get 테스트 내용");
        joke3.setCreateUserId(123L);
        joke3.setGreat(7L);
        jokeRepository.save(joke3);

        Joke joke4 = new Joke();
        joke4.setTitle("joke4");
        joke4.setDetail("Get 테스트 내용");
        joke4.setCreateUserId(123L);
        joke4.setGreat(9L);
        jokeRepository.save(joke4);

        List<Joke> jokeList = jokeRepository.findByTitleContainingOrderByGreatDesc("");
        assertEquals(joke1.getTitle(), jokeList.get(0).getTitle());
        assertEquals(joke4.getTitle(), jokeList.get(1).getTitle());
        assertEquals(joke3.getTitle(), jokeList.get(2).getTitle());
        assertEquals(joke2.getTitle(), jokeList.get(3).getTitle());
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
        Joke joke = new Joke();
        joke.setTitle("before changed title");
        joke.setDetail("before changed detail");
        joke.setCreateUserId(123L);
        joke.setGreat(10L);
        Joke savedJoke = jokeRepository.save(joke);

        JokeDto jokeDto = jokeService.getJoke(savedJoke.getJokeId());
        Long jokeId = jokeDto.getJokeId();

        jokeService.deleteJoke(jokeId);

        Optional<Joke> res = this.jokeRepository.findById(jokeId);
        assertTrue(res.isEmpty());
    }

    @Test
    void greatJoke() {
        Joke joke = new Joke();
        joke.setTitle("title");
        joke.setDetail("detail");
        joke.setCreateUserId(123L);
        joke.setGreat(0L);
        Joke savedJoke = jokeRepository.save(joke);

        jokeService.greatJoke(savedJoke.getJokeId(), "204");
        jokeService.greatJoke(savedJoke.getJokeId(), "205");
        jokeService.greatJoke(savedJoke.getJokeId(), "206");
        JokeDto jokeDto = JokeMapper.convertToDto(jokeRepository.findById(savedJoke.getJokeId()).get());
        
        assertEquals(3L, jokeDto.getGreat());
    }

}