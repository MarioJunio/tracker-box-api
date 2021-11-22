package br.com.trackerapi.repository;

import br.com.trackerapi.entity.TrackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<TrackEntity, String> {
}
