package br.com.trackerapi.resource.track.request;

import br.com.trackerapi.entity.CoordinateEntity;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TrackRequestDto {

    private int startSpeed;
    private int maxSpeed;
    private double distance;
    private long time;
    private Set<CoordinateEntity> coordinates = new HashSet<>();
    private String userId;
    private String checkSum;
}
