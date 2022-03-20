package br.com.trackerapi.resource.auth.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSigninDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
