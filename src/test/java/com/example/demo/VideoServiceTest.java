package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//https://github.com/dev-com2020/demo_spring_boot_amnis

public class VideoServiceTest {
    private VideoService videoService;

    @BeforeEach
    void setup(){
        videoService = new VideoService();
    }

    @Test
    void getVideos_shouldReturnInitialVideos(){
//        GIVEN
        List<Video> initialVideos = List.of(
                new Video("Spring boot"),
                new Video("Junit5"),
                new Video("Spock framework"));

//        WHEN
        List<Video> videos = videoService.getVideos();

//        THEN
        assertEquals(initialVideos.size(), videos.size());
        assertTrue(videos.containsAll(initialVideos));
    }
    @Test
    void create_shouldAddNewVideo(){
//        GIVEN
        Video newVideo = new Video("Nowy kurs");
//        WHEN
        videoService.create(newVideo);
//        THEN
        List<Video> videos = videoService.getVideos();
        assertTrue(videos.contains(newVideo));
    }

}
