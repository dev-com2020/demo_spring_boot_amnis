package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    @Mock
    private VideoService videoService;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void index_shouldReturnIndexViewWithVideos() {
//        GIVEN
        List<Video> videos = List.of(
                new Video("Springghghg boot"),
                new Video("Junit5555"),
                new Video("Spock fhkhkhkjramework"));
        when(videoService.getVideos()).thenReturn(videos);
        Model model = mock(Model.class);
//        WHEN
        String viewName = homeController.index(model);
//        THEN
        verify(model).addAttribute("videos",videos);
        assertEquals("index",viewName);
    }
    @Test
    void newVideo_shouldRedirectToIndexAfterCreation(){
//        GIVEN
        Video newVideo = new Video("Nowe wideo");
        when(videoService.create(newVideo)).thenReturn(newVideo);
//        WHEN
        String viewName = homeController.newVideo(newVideo);
//        THEN
        verify(videoService).create(newVideo);
        assertEquals("redirect:/", viewName);
    }
}
