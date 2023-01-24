package br.com.trackerapi.entity;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CoordinateEntity implements Serializable {

    @EqualsAndHashCode.Include
    private double latitude;

    @EqualsAndHashCode.Include
    private double longitude;
}
