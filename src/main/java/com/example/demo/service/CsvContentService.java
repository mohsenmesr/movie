package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CsvContentService {

  private Set<String> csvValues;

  @PostConstruct
  public void loadCsvFile() throws IOException {

    Path path = Paths.get("src/main/resources/static/oscar.csv");

    try (final Stream<String> lines = Files.lines(path)) {
      csvValues = lines
          .flatMap(line -> Stream.of(line.split(",")))
          .filter(StringUtils::hasLength)
          .map(String::toLowerCase)
          .map(String::trim)
          .collect(Collectors.toSet());
    }
  }

  public boolean contains(final String title) {
    return csvValues.contains(title.toLowerCase(Locale.ROOT));
  }
}
