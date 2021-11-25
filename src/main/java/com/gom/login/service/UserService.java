package com.gom.login.service;

import com.gom.login.constans.ErrorCode;
import com.gom.login.dto.RegisterUserDto;
import com.gom.login.entity.User;
import com.gom.login.error.GeneralException;
import com.gom.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Transactional
    public RegisterUserDto.Response registerUser(RegisterUserDto.Request request){
        if(userRepository.findById(request.getUserId()).isPresent()) {
            throw new GeneralException(ErrorCode.DUPLICATED_USER_ID);
        }
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return modelMapper.map(userRepository.save(user), RegisterUserDto.Response.class);
    }


}
