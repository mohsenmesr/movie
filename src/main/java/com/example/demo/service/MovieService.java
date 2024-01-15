package com.example.demo.service;

import com.example.demo.model.response.MovieRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final OmdbApi omdbApi;
  private final CsvContentService csvContentService;

  public MovieRes getMovieInfoByTitle(final String title) {
    return new MovieRes()
        .setMovieInfo(omdbApi.getMovieInfoByTitle(title))
        .setWonOscarEver(csvContentService.contains(title));
  }
}
