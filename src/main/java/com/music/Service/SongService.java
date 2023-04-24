package com.music.Service;
import com.music.Repository.songRepo.SongRepository;
import com.music.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SongService {
@Autowired
private SongRepository songRepository;
 
public Song saveSong(Song song){
        return songRepository.save(song);
    }

}
