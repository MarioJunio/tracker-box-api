package br.com.trackerapi.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoordinateEntity implements Serializable {
    private double latitude;
    private double longitude;
}
