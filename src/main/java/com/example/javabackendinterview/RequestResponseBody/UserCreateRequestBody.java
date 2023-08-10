package com.example.javabackendinterview.RequestResponseBody;

import lombok.Data;

@Data
public class UserCreateRequestBody {

    private String name;

    private String email;

    private String password;
}
