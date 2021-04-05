package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.RequestModel;
import jrogetdigitalharbor.back.ResponseModel;
import jrogetdigitalharbor.back.SearchingRequest;
import jrogetdigitalharbor.back.models.Doctor;
import jrogetdigitalharbor.back.repositories.DoctorRepository;
import jrogetdigitalharbor.back.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/doctors")
public class DoctorController extends BaseController {

    private DoctorRepository repository;
    private UserRepository userRepository;

    public DoctorController(DoctorRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public ResponseModel create(@RequestBody RequestModel<Doctor> request) {
        try {
            userRepository.findById(request.userId).get();
            Doctor newDoctor = new Doctor(request);
            Doctor doctorCreated = repository.save(newDoctor);
            return sendResponse("Doctor created.", doctorCreated);
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @GetMapping("")
    public ResponseModel readAll() {
        List<Doctor> doctors = this.repository.findAll();
        return sendResponse("doctors found.", doctors);
    }

    @GetMapping("/{id}")
    public ResponseModel readOne(@PathVariable String id) {
        Optional<Doctor> doctorFound = repository.findById(id);
        if (!doctorFound.isPresent()) {
            return sendResponse("Doctor not found.");
        }
        return sendResponse("Doctor found.", doctorFound.get());
    }

    @PutMapping("/{id}")
    public ResponseModel update(@RequestBody RequestModel<Doctor> request, @PathVariable String id) {
        try {
            userRepository.findById(request.userId).get();
            Doctor doctorFound = new Doctor(request);
            return sendResponse("Doctor updated.", this.repository.save(doctorFound));
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseModel delete(@PathVariable String id) {
        if (!repository.findById(id).isPresent()) {
            return sendResponse("Doctor not found.");
        }
        repository.deleteById(id);
        return sendResponse("Doctor deleted.");
    }

    @PostMapping("/search/name")
    public ResponseModel searchName(@RequestBody SearchingRequest request) {
        List<Doctor> doctors = repository.findByNameLike(request.word);
        return sendResponse("Doctors found.", doctors);
    }

    @PostMapping("/search/dates")
    public ResponseModel searchDates(@RequestBody SearchingRequest request) {
        List<Doctor> doctors = repository.findByDateOfBirthBetween(request.dateA, request.dateB);
        return sendResponse("Doctors found.", doctors);
    }
}
