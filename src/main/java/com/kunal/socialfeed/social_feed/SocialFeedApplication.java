package com.kunal.socialfeed.social_feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SocialFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialFeedApplication.class, args);
	}

}
