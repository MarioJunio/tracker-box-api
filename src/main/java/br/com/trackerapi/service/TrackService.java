package br.com.trackerapi.service;

import br.com.trackerapi.entity.TrackEntity;
import br.com.trackerapi.entity.UserEntity;
import br.com.trackerapi.repository.TrackRepository;
import br.com.trackerapi.repository.UserRepository;
import br.com.trackerapi.resource.track.request.TrackRequestDto;
import br.com.trackerapi.resource.track.response.TrackResponseDto;
import br.com.trackerapi.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackService {

    private final MapperUtil mapperUtil;

    private final TrackRepository trackRepository;
    private final UserRepository userRepository;

    public TrackResponseDto create(TrackRequestDto trackRequest) {
        log.info("M=create, trackRequest={}", trackRequest);

        final TrackEntity track = mapperUtil.convertTo(trackRequest, TrackEntity.class);
        log.info("M=create, track={}", track);

        final UserEntity user = userRepository
                .findById(trackRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info("M=create, user={}", user);

        track.setUser(user);

        //TODO: criar campos de coordenadas inicias do track e finais para fazer filtro

        final TrackEntity trackCreated = trackRepository.save(track);
        log.info("M=create, trackCreated={}", trackCreated);

        return mapperUtil.convertTo(trackCreated, TrackResponseDto.class);
    }

    public List<TrackResponseDto> readAll() {
        log.info("M=create, readAll");

        final List<TrackEntity> tracks = trackRepository.findAll();
        log.info("M=create, tracks={}", tracks);

        return mapperUtil.mapList(tracks, TrackResponseDto.class);
    }
}
