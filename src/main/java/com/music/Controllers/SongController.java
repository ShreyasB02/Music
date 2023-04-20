package com.music.Controllers;

import com.music.Repository.SongRepository;
import com.music.models.Song;
import com.music.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final StorageService storageService;
    private final SongRepository songRepository;
   
    
    @Autowired
    public SongController(StorageService storageService, SongRepository songRepository) {
        this.storageService = storageService;
        this.songRepository = songRepository;
    }

    @GetMapping
    public ResponseEntity<List<Song>> getSongs()
    {
        return ResponseEntity.ok(songRepository.findAll());
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

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createSong(@RequestPart("song")Song song, @RequestPart("file")MultipartFile file) throws IOException {
        if(songRepository.existsSongByFileNameEquals(file.getOriginalFilename()) || songRepository.existsSongByTitleEquals(song.getTitle()))
        {
            return ResponseEntity.badRequest().body("Name already exits");
        }
        else
        {
            System.out.println("Uploading the file...");
            storageService.uploadSong(file);

            song.setFileName(file.getOriginalFilename());
            Song insertedSong = songRepository.insert(song);

            return new ResponseEntity<>(insertedSong, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable String id,@RequestBody Song songData)
    {
        Optional<Song> songOptional = songRepository.findById(id);

        if(songOptional.isPresent())
        {
            Song song = songOptional.get();

            if (songData.getTitle() != null)
            {
                song.setTitle(songData.getTitle());
            }

            if (songData.getArtist() != null)
            {
                song.setArtist(songData.getArtist());
            }
            song.setFavorite(song.isFavorite());
            songRepository.save(song);

            return ResponseEntity.ok(song);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

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
