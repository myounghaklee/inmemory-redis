package com.example.jediscache.domain.entity;

import jakarta.persistence.Id;

import lombok.Builder;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Builder
@RedisHash(value = "redishash-user", timeToLive = 30L)
public class RedisHashUser {

    @Id
    private Long id;

    private String name;

    @Indexed
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
