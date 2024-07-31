package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeSpringTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @Test
    void index_shouldReturnIndexViewWithVideosInSpring() throws Exception{
//        GIVEN
        List<Video> videos = List.of(
                new Video("Springghghg boot"),
                new Video("Junit5555"),
                new Video("Spock fhkhkhkjramework"));
        given(videoService.getVideos()).willReturn(videos);
//        WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("videos",videos));

    }
    @Test
    void newVideo_shouldRedirectToIndexAfterCreationWithSpring() throws Exception {
//        GIVEN
        Video newVideo = new Video("Nowe wideo");
        given(videoService.create(newVideo)).willReturn(newVideo);
//        WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/new-video")
                .flashAttr("newVideo",newVideo))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void search_shouldReturnVideosContainQuery() throws Exception{
        String query = "Spring";
        List<Video> searchResults = List.of(new Video("Spring boot5"));
        given(videoService.searchByTitle(query)).willReturn(searchResults);

        mockMvc.perform(MockMvcRequestBuilders.get("/search").param("query",query))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("videos",searchResults));

    }
}

