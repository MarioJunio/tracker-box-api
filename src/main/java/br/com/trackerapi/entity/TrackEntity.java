package br.com.trackerapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
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

    private int startSpeed;
    private int maxSpeed;
    private double distance;
    private long time;
    private List<CoordinateEntity> coordinates = new ArrayList<>();

    @DBRef
    private UserEntity user;
}
