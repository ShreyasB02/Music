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

@Controller
public class IndexController {
    private final StorageService storageService;
    @Autowired
    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @GetMapping("/")
    public String Home(Model model){
        model.addAttribute("SongFileNames", storageService.getSongFileNames());
        model.addAttribute("songQueue", storageService.getSongQueue());
        return "index";
    }

    @PostMapping("/")
    public String uploadSong(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.uploadSong(file);
        return "redirect:/";
    }

    // @GetMapping("/getQueue")

}
