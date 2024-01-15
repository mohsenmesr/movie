package com.example.demo.controller;

import com.example.demo.model.response.MovieRes;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("movie")
public class MovieController {

  private final MovieService movieService;

  @GetMapping
  public MovieRes getMovieInfo(@RequestParam String title) {
    return movieService.getMovieInfoByTitle(title);
  }
}
