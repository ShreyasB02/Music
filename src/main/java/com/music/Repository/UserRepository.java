package com.music.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.music.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
