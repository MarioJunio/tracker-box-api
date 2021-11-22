package br.com.trackerapi.resource.user.request;

import lombok.Data;

@Data
public class UserRequestDto {

    private String username;
    private String password;
    private String fullname;
    private String color;
}
