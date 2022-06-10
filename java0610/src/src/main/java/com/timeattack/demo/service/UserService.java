package com.timeattack.demo.service;

import com.timeattack.demo.domain.UserRepository;
import com.timeattack.demo.domain.UserRequestDto;
import com.timeattack.demo.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long update(Long id, UserRequestDto requestDto) {
        Users user1 = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("No such ID found")
        );
        user1.update(requestDto);
        return user1.getId();
    }
}
