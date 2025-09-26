package com.kunal.socialfeed.social_feed.repository;


import com.kunal.socialfeed.social_feed.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}