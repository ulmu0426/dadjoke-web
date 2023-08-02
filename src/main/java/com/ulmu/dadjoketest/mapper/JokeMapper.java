package com.ulmu.dadjoketest.mapper;

import com.ulmu.dadjoketest.domain.Joke;
import com.ulmu.dadjoketest.dto.JokeDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JokeMapper {

    public static JokeDto convertToDto(Joke joke){
        JokeDto jokeDto = new JokeDto();
        jokeDto.setJokeId(joke.getJokeId());
        jokeDto.setJokeNum(joke.getJokeNum());
        jokeDto.setTitle(joke.getTitle());
        jokeDto.setDetail(joke.getDetail());
        jokeDto.setViews(joke.getViews());
        jokeDto.setCreateUserId(joke.getCreateUserId());
        jokeDto.setUploadedAt(joke.getUploadedAt());
        if(!joke.getGreatUserIDList().isEmpty()) {
            jokeDto.setGreatUserIdList(convertToList(joke.getGreatUserIDList()));
        }

        return jokeDto;
    }

    private static List<Long> convertToList(String greatUserIdList){
        return Stream.of(greatUserIdList.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static Joke convertToModel(JokeDto jokeDto){
        Joke joke = new Joke();
        joke.setJokeId(jokeDto.getJokeId());
        joke.setJokeNum(jokeDto.getJokeNum());
        joke.setTitle(jokeDto.getTitle());
        joke.setDetail(jokeDto.getDetail());
        joke.setViews(jokeDto.getViews());
        joke.setCreateUserId(jokeDto.getCreateUserId());
        joke.setUploadedAt(jokeDto.getUploadedAt());
        if(jokeDto.getGreatUserIdList() == null) {
            joke.setGreatUserIdList("");
        }else {
            joke.setGreatUserIdList(convertToList(jokeDto.getGreatUserIdList()));
        }

        return joke;
    }

    private static String convertToList(List<Long> greatUserIdList){
        return greatUserIdList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(","));
    }

    public static List<JokeDto> convertToDtoList(List<Joke> jokeList){
        return jokeList.stream()
                .map(JokeMapper::convertToDto)
                .collect(Collectors.toList());
    }

}
