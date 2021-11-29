package com.gom.login.controller;

import com.gom.login.dto.APIResponse;
import com.gom.login.dto.UserDto;
import com.gom.login.dto.RegisterUserDto;
import com.gom.login.entity.User;
import com.gom.login.error.GeneralException;
import com.gom.login.jwt.JwtAuthenticationProvider;
import com.gom.login.repository.UserRepository;
import com.gom.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.gom.login.constans.ErrorCode.ACCOUNT_INVALID;
import static com.gom.login.constans.ErrorCode.PASSWORD_INVALID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userSerivce;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @PostMapping("/signup")
    public APIResponse registerUser(@Valid @RequestBody final RegisterUserDto.Request request){
        return APIResponse.of(userSerivce.registerUser(request));
    }

    @PostMapping("/login")
    public APIResponse login(@RequestBody UserDto.Request user, HttpServletResponse response) {
        User member = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new GeneralException(ACCOUNT_INVALID));

        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new GeneralException(PASSWORD_INVALID);
        }

        String token = jwtAuthenticationProvider.createToken(member.getUserId(), member.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);

        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return APIResponse.of(UserDto.Response.builder()
                .userId(member.getUserId())
                .username(member.getUsername())
                .email(member.getEmail())
                .build());
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
