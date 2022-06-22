package com.talentboost.ics.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThrottlingConfiguration {

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(5.0 / 60);
    }
}
