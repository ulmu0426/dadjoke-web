package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.Joke;
import com.ulmu.dadjoketest.dto.JokeDto;
import com.ulmu.dadjoketest.mapper.JokeMapper;
import com.ulmu.dadjoketest.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class JokeService {
    @Autowired
    private JokeRepository jokeRepository;

    public JokeDto getJoke(Long jokeId){
        //개그 상세정보 불러오기
        Optional<Joke> res = jokeRepository.findById(jokeId);
        if(res.isPresent()){
            JokeDto jokeDto = JokeMapper.convertToDto(res.get());
            return jokeDto;
        }else {
            throw new EntityNotFoundException(String.format("해당 게시글이 %d로 조회되지 않았습니다.", jokeId));
        }
    }
    public List<JokeDto> getJokeList(String keyword, String sort, String orderBy) throws IllegalAccessException {
        //개그 리스트 불러오기
        List<Joke> jokes;
        if(Objects.equals(sort, "createdAt")){
            if(Objects.equals(orderBy, "desc")) {
                jokes = jokeRepository.findByJokeContainingOrderByCreatedAtDesc(keyword);
            }else {
                jokes = jokeRepository.findByJokeContainingOrderByCreatedAtAsc(keyword);
            }
        }else if(Objects.equals(sort, "great")){
            jokes = jokeRepository.findByJokeContainingOrderByGreatDesc(keyword);
        }else {
            throw new IllegalAccessException("정렬 기준이 없음");
        }

        return JokeMapper.convertToDtoList(jokes);
    }

    public JokeDto putJoke(Long jokeId, JokeDto jokeDto) {
        //개그 수정하기
        Optional<Joke> res = jokeRepository.findById(jokeId);
        if (res.isEmpty()){
            throw new NoSuchElementException(String.format("해당 개그 %d가 존재하지 않습니다", jokeId));
        }

        Joke updateJoke = res.get();
        updateJoke.setTitle(jokeDto.getTitle());
        updateJoke.setDetail(jokeDto.getDetail());
        Joke savedJoke = this.jokeRepository.save(updateJoke);

        return JokeMapper.convertToDto(savedJoke);
    }

    public void deleteJoke(Long jokeId) {
        Optional<Joke> res = this.jokeRepository.findById(jokeId);
        if(res.isEmpty()){
            throw new NoSuchElementException(String.format("해당 개그 %d가 존재하지 않습니다", jokeId));
        }

        this.jokeRepository.delete(res.get());
    }

    public JokeDto greatJoke(Long jokeId, String greatUserId){
        Optional<Joke> res = jokeRepository.findById(jokeId);
        if(res.isEmpty()){
            throw new NoSuchElementException(String.format("해당 개그 %d가 존재하지 않습니다", jokeId));
        }
        Joke updateGreatJoke = res.get();
        updateGreatJoke.setGreatUserIdList(greatUserId);
        Joke savedJoke = this.jokeRepository.save(updateGreatJoke);

        return JokeMapper.convertToDto(savedJoke);
    }

    public JokeDto createJoke(JokeDto jokeDto) {
        Joke newJoke = JokeMapper.convertToModel(jokeDto);
        this.jokeRepository.save(newJoke);

        return JokeMapper.convertToDto(newJoke);
    }
}
