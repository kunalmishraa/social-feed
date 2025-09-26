package com.kunal.socialfeed.social_feed.repository;

import com.kunal.socialfeed.social_feed.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
