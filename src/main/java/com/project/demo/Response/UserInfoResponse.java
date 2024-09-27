package com.project.demo.Response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoResponse <T>{
    private T Data;
    private boolean Success;
    private String Message;

    public UserInfoResponse(T data, boolean success, String message) {
        Data = data;
        Success = success;
        Message = message;
    }

}
