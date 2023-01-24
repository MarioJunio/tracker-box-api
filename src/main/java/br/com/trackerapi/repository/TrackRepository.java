package br.com.trackerapi.repository;

import br.com.trackerapi.entity.TrackEntity;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackRepository extends MongoRepository<TrackEntity, String> {

    @Aggregation(pipeline = "{$geoNear: {near: {type:\"Point\", coordinates:[?0, ?1]}, distanceField: \"straightDistance\", maxDistance: ?2, spherical: true}}")
    AggregationResults<TrackEntity> readByDistance(double latitude, double longitude, double maxDistance);

    Optional<TrackEntity> findByCheckSum(String checkSum);
}
