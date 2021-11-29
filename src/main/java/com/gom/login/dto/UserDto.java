package com.gom.login.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotNull
        private String userId;
        @NotNull
        private String password;
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
