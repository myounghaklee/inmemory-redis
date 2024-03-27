package com.example.jediscache.service;


import com.example.jediscache.UserRepository;
import com.example.jediscache.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User getUser(final Long id){
        return userRepository.findById(id).orElseThrow();
    }
}
