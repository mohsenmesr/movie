package com.example.demo.service;

import java.time.Duration;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbApi {

  private static final RestTemplate restTemplate = new RestTemplateBuilder()
      .setConnectTimeout(Duration.ofSeconds(10))
      .setReadTimeout(Duration.ofSeconds(10))
      .build();

  @Value("${omdb.url}")
  private String url;

  public Map<String, Object> getMovieInfoByTitle(String title) {
    return restTemplate.getForObject(url, Map.class, title);
  }
}
