package br.com.trackerapi.resource.user.response;

import lombok.Data;

@Data
public class UserResponseDto {

    private String id;
    private String username;
    private String fullname;
    private String color;
}
