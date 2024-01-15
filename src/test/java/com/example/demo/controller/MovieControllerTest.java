package com.example.demo.controller;

import com.example.demo.service.CsvContentService;
import com.example.demo.service.OmdbApi;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTest {

  @MockBean
  OmdbApi omdbApi;

  @MockBean
  CsvContentService csvContentService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getMovieInfoTest() throws Exception {
    when(omdbApi.getMovieInfoByTitle(anyString()))
        .thenReturn(Collections.singletonMap("title", "test"));

    when(csvContentService.contains(anyString()))
        .thenReturn(true);

    String title = "test";

    mockMvc.perform(get("/movie")
            .param("title", title))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.wonOscarEver", is(true)))
        .andExpect(jsonPath("$.movieInfo", notNullValue()))
        .andExpect(jsonPath("$.movieInfo.title", is(title)));
  }
}