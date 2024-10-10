package me.leechanghwan.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;

import me.leechanghwan.springbootdeveloper.domain.User;
import me.leechanghwan.springbootdeveloper.dto.AddUserRequest;
import me.leechanghwan.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    // 유저 ID로 유저를 검색해서 전달하는 findById() 메서드 추가
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}