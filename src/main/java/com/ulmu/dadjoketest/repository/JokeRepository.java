package com.ulmu.dadjoketest.repository;

import com.ulmu.dadjoketest.domain.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {
    //최신순 정렬
    List<Joke> findByJokeContainingOrderByCreatedAtDesc(String keyword);

    //등록순 정렬
    List<Joke> findByJokeContainingOrderByCreatedAtAsc(String keyword);

    //추천순 정렬(높은순서)
    List<Joke> findByJokeContainingOrderByGreatDesc(String keyword);

}
