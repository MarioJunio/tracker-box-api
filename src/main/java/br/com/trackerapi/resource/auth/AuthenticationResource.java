package br.com.trackerapi.resource.auth;

import br.com.trackerapi.entity.UserEntity;
import br.com.trackerapi.resource.auth.request.UserSigninDto;
import br.com.trackerapi.resource.auth.response.UserAuthDto;
import br.com.trackerapi.util.JwtTokenUtil;
import br.com.trackerapi.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/auth")
public class AuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final MapperUtil mapperUtil;

    @PostMapping("/signin")
    public ResponseEntity<UserAuthDto> signin(@RequestBody @Valid UserSigninDto userSignin) {
        final Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userSignin.getUsername(), userSignin.getPassword()));

        final UserEntity user = (UserEntity) authenticate.getPrincipal();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(user))
                .body(mapperUtil.convertTo(user, UserAuthDto.class));
    }

}
