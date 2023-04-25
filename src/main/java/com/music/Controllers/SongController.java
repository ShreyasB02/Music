package com.music.Controllers;

import com.music.Repository.songRepo.SongRepository;
import com.music.models.Song;
import com.music.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    @Qualifier("songMongoTemplate")
    private MongoTemplate songMongoTemplate;
    private final StorageService storageService;
    private final SongRepository songRepository;
   
    
    @Autowired
    public SongController(StorageService storageService, SongRepository songRepository) {
        this.storageService = storageService;
        this.songRepository = songRepository;
    }

    @GetMapping("/getSong")
    public void getSongs()
    {
        Song u = songRepository.findAll().get(0);
        System.out.println("********************************************************************");
        System.out.println(u.toString());
        System.out.println("********************************************************************");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable String id)
    {
        Optional<Song> song = songRepository.findById(id);
        if(song.isPresent())
        {
            return ResponseEntity.ok(song.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find")
    public String searchSong(@RequestParam("title") String title, Model model)
    {
        Song foundSong = songRepository.findBytitle(title);
        model.addAttribute("foundSong",foundSong);
        System.out.println(foundSong.getArtist());
        return "Search";
    }
    @GetMapping("/musicform")
    public String displayMusicForm(Model model)
    {
        Song newSong = new Song();
        model.addAttribute("newSong",newSong);
        return "music_form";
    }
    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public String createSong(@RequestPart("title")String title, @RequestPart("artist")String artist, @RequestPart("file")MultipartFile file) throws IOException {
        if(songRepository.existsSongByFileNameEquals(file.getOriginalFilename()) || songRepository.existsSongByTitleEquals(title))
        {
            return "error1";
        }
        else
        {
            System.out.println("Uploading the file...");
            storageService.uploadSong(file);
            Song newSong = new Song();
            newSong.setArtist(artist);
            newSong.setTitle(title);
            newSong.setFileName(file.getOriginalFilename());
            Song insertedSong = songRepository.save(newSong);
            storageService.uploadSong(file);

            return "redirect:/";
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Song> updateSong(@PathVariable String id,@RequestBody Song songData)
//    {
//        Optional<Song> songOptional = songRepository.findById(id);
//
//        if(songOptional.isPresent())
//        {
//            Song song = songOptional.get();
//
//            if (songData.getTitle() != null)
//            {
//                song.setTitle(songData.getTitle());
//            }
//
//            if (songData.getArtist() != null)
//            {
//                song.setArtist(songData.getArtist());
//            }
//            song.setFavorite(song.isFavorite());
//            songRepository.save(song);
//
//            return ResponseEntity.ok(song);
//        }
//        else
//        {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable String id)
    {
        if (songRepository.existsById(id))
        {
            songRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}