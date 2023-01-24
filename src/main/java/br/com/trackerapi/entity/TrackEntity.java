package br.com.trackerapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("tracks")
public class TrackEntity implements Serializable {

    @Id
    private String id;

    // check sum using SHA256 calculated in app to distinct tracks
    private String checkSum;

    private int startSpeed;
    private int maxSpeed;

    // in Meters
    private double distance;
    private long time;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint startCoordinate;

    private GeoJsonPoint endCoordinate;

    private List<CoordinateEntity> coordinates = new ArrayList<>();

    @DBRef
    private UserEntity user;

    private Double straightDistance;

}
