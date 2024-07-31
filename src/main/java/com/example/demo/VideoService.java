package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {

    private List<Video> videos = List.of(
            new Video("Spring boot"),
            new Video("Junit5"),
            new Video("Spock framework"));

    public List<Video> getVideos() {
        return videos;
    }

    public Video create(Video newVideo){
        List<Video> extend = new ArrayList<>(videos);
        extend.add(newVideo);
        this.videos = List.copyOf(extend);
        return newVideo;
    }

    public List<Video> searchByTitle(String query){
        return videos.stream()
                .filter(video -> video.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

}
