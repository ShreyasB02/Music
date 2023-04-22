package com.music.Repository.songRepo;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.music.models.Song;
public interface SongRepository extends MongoRepository<Song, String>  {
    
    boolean existsSongByFileNameEquals(String fileName);

    boolean existsSongByTitleEquals(String title);
}
