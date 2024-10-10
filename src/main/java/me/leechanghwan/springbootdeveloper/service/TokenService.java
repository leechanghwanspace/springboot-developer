package me.leechanghwan.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;

import me.leechanghwan.springbootdeveloper.config.jwt.TokenProvider;
import me.leechanghwan.springbootdeveloper.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    // createNewAccessToken() 메서드로 전달받은 리프레시 토큰으로 토큰 유효성 검사를 진행한다.
    // 유효한 토큰일 경우 리프레시 토큰으로 사용자 ID 찾기
    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
