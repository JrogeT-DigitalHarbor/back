package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<Doctor, String> {}
