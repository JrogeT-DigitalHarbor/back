package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HospitalRepository extends MongoRepository<Hospital, String> {}
