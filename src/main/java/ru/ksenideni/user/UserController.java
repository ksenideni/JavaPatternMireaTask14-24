package ru.ksenideni.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registration")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @PostMapping
    public void registration(@RequestBody UserDTO user) {
        userDao.create(user);
    }
}
