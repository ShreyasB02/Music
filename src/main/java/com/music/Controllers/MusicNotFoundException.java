package com.music.Controllers;

public class MusicNotFoundException extends Throwable {
    public MusicNotFoundException(String message) {
        super(message);
    }
}
