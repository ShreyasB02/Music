package com.music.Repository.userRepo;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.music.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByUsername(String username);
}
