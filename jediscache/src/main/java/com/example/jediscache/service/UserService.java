package com.example.jediscache.service;


import com.example.jediscache.UserRepository;
import com.example.jediscache.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, User> userRedisTemplate;

    @Transactional
    public User getUser(final Long id){
        var key = "users:%d".formatted(id);
        var cacheUser = userRedisTemplate.opsForValue().get(key);

        if(cacheUser != null){
            return cacheUser;
        }
        User user = userRepository.findById(id).orElseThrow();

        userRedisTemplate.opsForValue().set(key, user);

        return user;
    }
}
