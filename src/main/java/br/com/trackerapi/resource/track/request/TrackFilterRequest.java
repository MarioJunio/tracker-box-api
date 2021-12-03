package br.com.trackerapi.resource.track.request;

import br.com.trackerapi.entity.CoordinateEntity;
import lombok.Data;

@Data
public class TrackFilterRequest {

    private CoordinateEntity currentCoordinate;
    private double maxDistance;
}
