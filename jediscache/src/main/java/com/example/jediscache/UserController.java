package com.example.jediscache;


import com.example.jediscache.domain.entity.User;
import com.example.jediscache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}/email")
    public User getUserEmail(@PathVariable Long id) {
        return userService.getUser(id);
    }

}
