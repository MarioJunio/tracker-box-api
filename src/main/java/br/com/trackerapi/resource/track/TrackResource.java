package br.com.trackerapi.resource.track;

import br.com.trackerapi.resource.track.request.TrackRequestDto;
import br.com.trackerapi.resource.track.response.TrackResponseDto;
import br.com.trackerapi.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tracks")
@RequiredArgsConstructor
public class TrackResource {

    private final TrackService trackService;

    @PostMapping
    public ResponseEntity<TrackResponseDto> publish(@RequestBody TrackRequestDto trackRequest) {
        log.info("M=publish, trackRequest={}", trackRequest);

        TrackResponseDto trackResponse = trackService.create(trackRequest);
        log.info("M=publish, trackResponse={}", trackResponse);

        return ResponseEntity.ok(trackResponse);
    }

    @GetMapping
    public ResponseEntity<List<TrackResponseDto>> listAll() {
        log.info("M=publish, listAll");

        final List<TrackResponseDto> tracks = trackService.readAll();
        log.info("M=publish, tracks={}", tracks);

        return ResponseEntity.ok(tracks);
    }
}