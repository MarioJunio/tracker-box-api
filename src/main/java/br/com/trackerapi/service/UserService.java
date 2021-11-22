package br.com.trackerapi.service;

import br.com.trackerapi.entity.UserEntity;
import br.com.trackerapi.repository.UserRepository;
import br.com.trackerapi.resource.user.request.UserRequestDto;
import br.com.trackerapi.resource.user.response.UserResponseDto;
import br.com.trackerapi.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final MapperUtil mapperUtil;

    public UserResponseDto create(UserRequestDto user) {
        log.info("M=create, user={}", user);
        final UserEntity userCreated = userRepository.save(mapperUtil.convertTo(user, UserEntity.class));
        log.info("M=create, userCreated={}", userCreated);

        return mapperUtil.convertTo(userCreated, UserResponseDto.class);
    }

    public List<UserResponseDto> readAll() {
        log.info("M=readAll, retriving users");
        List<UserEntity> users = userRepository.findAll();
        log.info("M=readAll, users={}", users);

        return mapperUtil.mapList(users, UserResponseDto.class);
    }
}
