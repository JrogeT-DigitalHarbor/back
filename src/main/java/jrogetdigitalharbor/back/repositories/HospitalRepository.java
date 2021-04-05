package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface HospitalRepository extends MongoRepository<Hospital, String> {
    List<Hospital> findByNameLike(String regexp);
    List<Hospital> findByCreatedDateBetween(Instant dateA, Instant dateB);
}
