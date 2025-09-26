package com.kunal.socialfeed.social_feed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String content;
    private User user;   // author of the post
    private List<String> likes = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();  // ✅ now uses your model
    private Instant createdAt = Instant.now();
}
