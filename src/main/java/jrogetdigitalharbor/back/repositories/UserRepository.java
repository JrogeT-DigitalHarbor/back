package jrogetdigitalharbor.back.repositories;

import jrogetdigitalharbor.back.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {}
