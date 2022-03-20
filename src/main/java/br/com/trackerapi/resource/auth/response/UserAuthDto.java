package br.com.trackerapi.resource.auth.response;

import lombok.Data;

@Data
public class UserAuthDto {

    private String username;
    private String fullname;
    private String color;
}
