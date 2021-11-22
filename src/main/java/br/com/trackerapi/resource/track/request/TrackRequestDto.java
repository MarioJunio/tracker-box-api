package br.com.trackerapi.resource.track.request;

import br.com.trackerapi.entity.CoordinateEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrackRequestDto {

    private int startSpeed;
    private int maxSpeed;
    private double distance;
    private long time;
    private List<CoordinateEntity> coordinates = new ArrayList<>();
    private String userId;
}
