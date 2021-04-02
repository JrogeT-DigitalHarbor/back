package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.Appoinment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appoinment, String> {}
