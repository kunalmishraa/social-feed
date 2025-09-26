package com.kunal.socialfeed.social_feed.controller;

import com.kunal.socialfeed.social_feed.model.Comment;
import com.kunal.socialfeed.social_feed.model.Post;
import com.kunal.socialfeed.social_feed.model.User;
import com.kunal.socialfeed.social_feed.repository.PostRepository;
import com.kunal.socialfeed.social_feed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping
    public Post createPost(@RequestBody Post post, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        post.setUser(user);
        Post saved = postRepository.save(post);

        // Broadcast to WebSocket
        simpMessagingTemplate.convertAndSend("/topic/posts", saved);
        return saved;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/{id}/like")
    public Post likePost(@PathVariable String id, Authentication authentication) {
        Post post = postRepository.findById(id).orElseThrow();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        post.getLikes().add(user.getId());
        Post updated = postRepository.save(post);

        simpMessagingTemplate.convertAndSend("/topic/posts", updated);
        return updated;
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<Post> addComment(@PathVariable String id,
                                           @RequestBody String text,
                                           Principal principal) {
        Post post = postRepository.findById(id).orElseThrow();
        Comment comment = new Comment(principal.getName(), text, Instant.now());
        post.getComments().add(comment);
        Post updated = postRepository.save(post);
        return ResponseEntity.ok(updated);


//    @PostMapping
//    public ResponseEntity<Post> createPost(@RequestBody Post post, Principal principal) {
//        // ✅ get user from JWT
//        post.setUserId(principal.getName());
//
//        Post saved = postRepository.save(post);
//        return ResponseEntity.ok(saved);
//    }

    }
}
