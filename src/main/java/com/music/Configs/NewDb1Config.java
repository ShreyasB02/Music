package com.music.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.music.Repository.userRepo"},
        mongoTemplateRef = NewDb1Config.MONGO_TEMPLATE
)
public class NewDb1Config {
    protected static final String MONGO_TEMPLATE = "userMongoTemplate";
}
