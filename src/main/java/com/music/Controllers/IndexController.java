package com.music.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.music.services.StorageService;
import com.music.Service.SongService;
import com.music.models.Song;

@Controller
public class IndexController {
    private final StorageService storageService;
    private final SongService songService;
    @Autowired
    public IndexController(StorageService storageService, SongService songService) {
        this.storageService = storageService;
        this.songService = songService;
    }
    
    @GetMapping("/")
    public String Home(Model model){
        model.addAttribute("SongFileNames", storageService.getSongFileNames());
        // model.addAttribute("songQueue", storageService.getSongQueue());
        return "index";
    }
    @GetMapping("/myQueue")
    public String Queue(Model model){
        return "myQueue";
    }

    @GetMapping("/new")
    public String uploadSongDetails(Model model) throws IOException {
        model.addAttribute("Song", new Song());
        return "music_form";
    }

    @PostMapping("/upload")
    public String uploadSong(Song song) throws IOException {
        songService.saveSong(song);
        //storageService.uploadSong(file);
        return "redirect:/";
    }

    // @GetMapping("/getQueue")

}
