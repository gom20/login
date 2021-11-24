package com.gom.login.controller;

import com.gom.login.dto.APIResponse;
import com.gom.login.dto.RegisterUserDto;
import com.gom.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userSerivce;


    @PostMapping("/signup")
    public APIResponse registerUser(@Valid @RequestBody final RegisterUserDto.Request request){
        return APIResponse.of(userSerivce.registerUser(request));
    }


}
