package com.timeattack.demo.controller;

import com.timeattack.demo.domain.UserRepository;
import com.timeattack.demo.domain.UserRequestDto;
import com.timeattack.demo.domain.Users;
import com.timeattack.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userservice;

    @GetMapping("/read/users")
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/create/users")
    public Users createUsers(@RequestBody UserRequestDto requestDto) {
        Users user = new Users(requestDto);
        return userRepository.save(user);
    }

    @PutMapping("/update/{id}")
    public Long updateUsers(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @RequestMapping("/recommend/{id}")
    public Long recommendUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userRepository.findbyGender();
    }

}
