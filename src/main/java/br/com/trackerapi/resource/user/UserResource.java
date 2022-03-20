package br.com.trackerapi.resource.user;

import br.com.trackerapi.resource.user.request.UserRequestDto;
import br.com.trackerapi.resource.user.response.UserResponseDto;
import br.com.trackerapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserResource {

    private final UserService userService;

    @PostMapping
    private ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user) {
        log.info("M=create, user={}", user);

        final UserResponseDto userResponseDto = userService.create(user);
        log.info("M=create, userResponseDto={}", userResponseDto);

        return ResponseEntity
                .created(null)
                .body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> listAll() {
        log.info("M=listAll");
        return ResponseEntity.ok(userService.readAll());
    }
}
