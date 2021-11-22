package br.com.trackerapi.resource.track.response;

import br.com.trackerapi.entity.CoordinateEntity;
import br.com.trackerapi.resource.user.response.UserResponseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrackResponseDto {

    private String id;
    private int startSpeed;
    private int maxSpeed;
    private double distance;
    private long time;
    private List<CoordinateEntity> coordinates = new ArrayList<>();
    private UserResponseDto user;
}
