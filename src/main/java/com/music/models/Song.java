package com.music.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Song {
    @Id
    private String id;
    
    private String fileName;
    
    private String title;
    
    private String artist;
    
    private boolean isFavorite;
}
