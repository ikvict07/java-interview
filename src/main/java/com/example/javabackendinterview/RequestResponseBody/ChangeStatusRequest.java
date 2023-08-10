package com.example.javabackendinterview.RequestResponseBody;

import com.example.javabackendinterview.model.UserStatus;
import lombok.Data;

@Data
public class ChangeStatusRequest {
    private UserStatus status;
    private Long id;
}
