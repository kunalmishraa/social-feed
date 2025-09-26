package com.kunal.socialfeed.social_feed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String userId;       // who commented
    private String text;         // comment content
    private Instant createdAt = Instant.now();
}
