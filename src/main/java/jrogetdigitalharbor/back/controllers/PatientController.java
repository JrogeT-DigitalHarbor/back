package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.RequestModel;
import jrogetdigitalharbor.back.ResponseModel;
import jrogetdigitalharbor.back.SearchingRequest;
import jrogetdigitalharbor.back.models.Appointment;
import jrogetdigitalharbor.back.models.Patient;
import jrogetdigitalharbor.back.repositories.PatientRepository;
import jrogetdigitalharbor.back.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
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
    public ResponseModel delete(@PathVariable String id) {
        if (!repository.findById(id).isPresent()) {
            return sendResponse(modelName + " not found.");
        }
        repository.deleteById(id);
        return sendResponse(modelName + " deleted.");
    }

    @PostMapping("/{patientId}/appointments")
    public ResponseModel addAppointment(@RequestBody RequestModel<Appointment> request, @PathVariable String patientId) {
        try {
            userRepository.findById(request.userId).get();
            Patient patientToUpdate = repository.findById(patientId).get();
            Appointment newAppointment = new Appointment(request);
            patientToUpdate.appointments.add(newAppointment);
            Patient patientUpdated = repository.save(patientToUpdate);
            return sendResponse("Appointment created.", patientUpdated);
        } catch (NoSuchElementException e) {
            return sendResponse("User/Patient not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @PostMapping("/search/name")
    public ResponseModel searchName(@RequestBody SearchingRequest request) {
        List<Patient> patients = repository.findByNameLike(request.word);
        return sendResponse("Patients found.", patients);
    }

    @PostMapping("/search/dates")
    public ResponseModel searchDates(@RequestBody SearchingRequest request) {
        List<Patient> patients = repository.findByDateOfBirthBetween(request.dateA, request.dateB);
        return sendResponse("Patients found.", patients);
    }
}
