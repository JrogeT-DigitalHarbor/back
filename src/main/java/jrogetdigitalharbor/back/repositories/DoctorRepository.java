package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findByNameLike(String regexp);
    List<Doctor> findByDateOfBirthBetween(Instant dateA, Instant dateB);
}
