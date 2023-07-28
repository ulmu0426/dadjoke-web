package com.ulmu.dadjoketest.controller;

import com.ulmu.dadjoketest.domain.Joke;
import com.ulmu.dadjoketest.dto.JokeDto;
import com.ulmu.dadjoketest.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/joke")
public class JokeController {
    @Autowired
    JokeService jokeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<JokeDto>> getJokeList(@RequestParam(value = "keyword", required = false, defaultValue = "") final String keyword,
                                                     @RequestParam(value = "sort", required = false, defaultValue = "byDate") final String sort,
                                                     @RequestParam(value = "orderBy", required = false, defaultValue = "desc") final String orderBy) throws IllegalAccessException {
        //아재개그 리스트요청 응답
        List<JokeDto> jokeDtoList = jokeService.getJokeList(keyword, sort, orderBy);

        return new ResponseEntity<>(jokeDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{jokeId}", method = RequestMethod.GET)
    public ResponseEntity<JokeDto> getJoke(@PathVariable("jokeId") final Long jokeId) {
        //아재개그 상세정보 요청 응답
        JokeDto jokeDto = jokeService.getJoke(jokeId);
        return new ResponseEntity<>(jokeDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{jokeId}", method = RequestMethod.PUT)
    public ResponseEntity<JokeDto> putJoke(@PathVariable("jokeId") final Long jokeId,
                                           @RequestBody final JokeDto jokeDto) {
        //아재개그 내용 수정(제목, 내용) 요청 응답
        JokeDto res = jokeService.putJoke(jokeId, jokeDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/{jokeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteJoke(@PathVariable("jokeId") final Long jokeId){
        //아재개그 삭제 요청 응답
        jokeService.deleteJoke(jokeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{jokeId}/great", method = RequestMethod.POST)
    public ResponseEntity<JokeDto> greatJoke(@PathVariable("jokeId") final Long jokeId,
                                             @RequestBody final String greatUserId){
        //아재개그 추천 요청 응답
        JokeDto jokeDto = jokeService.greatJoke(jokeId, greatUserId);
        return new ResponseEntity<>(jokeDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<JokeDto> createJoke(@RequestBody final JokeDto jokeDto){
        JokeDto newJoke = jokeService.createJoke(jokeDto);
        return new ResponseEntity<>(newJoke, HttpStatus.OK);
    }
}
