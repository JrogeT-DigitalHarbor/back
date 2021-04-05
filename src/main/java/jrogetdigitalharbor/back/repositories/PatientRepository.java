package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findByNameLike(String regexp);

    List<Patient> findByLastnameLike(String regexp);

    List<Patient> findByDateOfBirthBetween(Instant dateA, Instant dateB);
}
