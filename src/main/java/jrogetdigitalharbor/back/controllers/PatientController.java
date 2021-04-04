package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.RequestModel;
import jrogetdigitalharbor.back.ResponseModel;
import jrogetdigitalharbor.back.models.Patient;
import jrogetdigitalharbor.back.repositories.PatientRepository;
import jrogetdigitalharbor.back.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController extends BaseController {

    private PatientRepository repository;
    private UserRepository userRepository;
    private String modelName = "Patient";

    public PatientController(PatientRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public ResponseModel create(@RequestBody RequestModel<Patient> request) {
        try {
            userRepository.findById(request.userId).get();
            Patient newPatient = new Patient(request);
            Patient patientCreated = repository.save(newPatient);
            return sendResponse(modelName + " created.", patientCreated);
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @GetMapping("")
    public ResponseModel readAll() {
        List<Patient> patients = this.repository.findAll();
        return sendResponse(modelName + " found.", patients);
    }

    @GetMapping("/{id}")
    public ResponseModel readOne(@PathVariable String id) {
        Optional<Patient> patientFound = repository.findById(id);
        if (!patientFound.isPresent()) {
            return sendResponse(modelName + " not found.");
        }
        return sendResponse(modelName + " found.", patientFound.get());
    }

    @PutMapping("/{id}")
    public ResponseModel update(@RequestBody RequestModel<Patient> request, @PathVariable String id) {
        try {
            userRepository.findById(request.userId).get();
            Patient patientFound = new Patient(request);
            return sendResponse(modelName + " updated.", this.repository.save(patientFound));
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseModel deleteEmployee(@PathVariable String id) {
        if (!repository.findById(id).isPresent()) {
            return sendResponse(modelName + " not found.");
        }
        repository.deleteById(id);
        return sendResponse(modelName + " deleted.");
    }
}
