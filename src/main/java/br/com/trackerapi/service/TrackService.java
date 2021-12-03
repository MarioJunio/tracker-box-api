package br.com.trackerapi.service;

import br.com.trackerapi.entity.CoordinateEntity;
import br.com.trackerapi.entity.TrackEntity;
import br.com.trackerapi.entity.UserEntity;
import br.com.trackerapi.exception.TrackCoordinateEmptyException;
import br.com.trackerapi.repository.TrackRepository;
import br.com.trackerapi.repository.UserRepository;
import br.com.trackerapi.resource.track.request.TrackFilterRequest;
import br.com.trackerapi.resource.track.request.TrackRequestDto;
import br.com.trackerapi.resource.track.response.TrackResponseDto;
import br.com.trackerapi.util.DistanceConverters;
import br.com.trackerapi.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackService {

    private final MapperUtil mapperUtil;

    private final TrackRepository trackRepository;
    private final UserRepository userRepository;

    public List<TrackResponseDto> filterByDistance(TrackFilterRequest filter) {
        log.info("M=filterByDistance");

        AggregationResults<TrackEntity> trackEntities = trackRepository.readByDistance(filter.getCurrentCoordinate().getLatitude(), filter.getCurrentCoordinate().getLongitude(), DistanceConverters.kmToMeters(filter.getMaxDistance()));
        List<TrackEntity> tracks = trackEntities.getMappedResults();

        log.info("M=filterByDistance, tracks={}", tracks);

        return mapperUtil.mapList(tracks, TrackResponseDto.class);
    }

    public List<TrackResponseDto> readAll() {
        log.info("M=readAll");

        final List<TrackEntity> tracks = trackRepository.findAll();
        log.info("M=readAll, tracks={}", tracks);

        return mapperUtil.mapList(tracks, TrackResponseDto.class);
    }

    public TrackResponseDto create(TrackRequestDto trackRequest) {
        log.info("M=create, trackRequest={}", trackRequest);

        final TrackEntity track = mapperUtil.convertTo(trackRequest, TrackEntity.class);
        log.info("M=create, track={}", track);

        final UserEntity user = userRepository
                .findById(trackRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info("M=create, user={}", user);

        track.setUser(user);
        track.setStartCoordinate(getStartCoordinateFromTrack(track));
        track.setEndCoordinate(getEndCoordinateFromTrack(track));

        final TrackEntity trackCreated = trackRepository.save(track);
        log.info("M=create, trackCreated={}", trackCreated);

        return mapperUtil.convertTo(trackCreated, TrackResponseDto.class);
    }

    private GeoJsonPoint getStartCoordinateFromTrack(TrackEntity track) {
        final CoordinateEntity coordinate = track.getCoordinates()
                .stream()
                .findFirst()
                .orElseThrow(() -> new TrackCoordinateEmptyException("Track não possui rota"));

        return new GeoJsonPoint(coordinate.getLatitude(), coordinate.getLongitude());
    }

    private GeoJsonPoint getEndCoordinateFromTrack(TrackEntity track) {
        final int size = track.getCoordinates().size();
        final CoordinateEntity coordinate = track.getCoordinates().get(size - 1);

        return new GeoJsonPoint(coordinate.getLatitude(), coordinate.getLongitude());
    }
}
