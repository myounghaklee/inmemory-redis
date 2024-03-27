package com.example.jediscache.service;


import com.example.jediscache.domain.entity.RedisHashUser;
import com.example.jediscache.domain.entity.repository.RedisHashUserRepository;
import com.example.jediscache.domain.entity.repository.UserRepository;
import com.example.jediscache.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisHashUserRepository redisHashUserRepository;
    private final RedisTemplate<String, User> userRedisTemplate;

    private final RedisTemplate<String, Object> objectRedisTemplate;

    @Transactional
    public User getUser(final Long id){
        var key = "users:%d".formatted(id);
        var cacheUser = objectRedisTemplate.opsForValue().get(key);

        if(cacheUser != null){
            return (User) cacheUser;
        }
        User user = userRepository.findById(id).orElseThrow();

        userRedisTemplate.opsForValue().set(key, user);

        return user;
    }

    @Transactional
    public RedisHashUser getUser2(final Long id){
        var cacheuser = redisHashUserRepository.findById(id).orElseGet(() -> {
                    User user = userRepository.findById(id).orElseThrow();
                    return redisHashUserRepository.save(RedisHashUser.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .name(user.getName())
                            .createdAt(user.getCreatedAt())
                            .updatedAt(user.getUpdatedAt())
                            .build());
                });
        return cacheuser;
    }
}
