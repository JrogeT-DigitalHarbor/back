package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {}