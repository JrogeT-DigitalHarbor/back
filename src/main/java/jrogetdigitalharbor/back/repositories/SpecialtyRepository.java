package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.Specialty;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecialtyRepository extends MongoRepository<Specialty, String> {}
