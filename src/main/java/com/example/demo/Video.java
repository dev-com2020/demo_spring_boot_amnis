package com.example.demo;


import java.util.Objects;

public record Video(String name) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(name, video.name);
    }

    public String getTitle(){
        return name;
    }
}
