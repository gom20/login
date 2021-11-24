package com.gom.login.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RegisterUserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotNull
        @Size(min = 1, max = 20, message = "userId size must be 1 to 20" )
        private String userId;

        @NotNull
        @Size(min = 8, max = 20, message = "password size must be 8 to 20" )
        private String password;

        @NotNull
        @Size(min = 1, max = 20, message = "userName size must be 1 to 20" )
        private String username;

        @NotNull
        @Size(min = 1, max = 50, message = "email size must be 1 to 50" )
        private String email;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String userId;
        private String username;
        private String email;
    }

}
