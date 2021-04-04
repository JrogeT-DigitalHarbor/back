package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.RequestModel;
import jrogetdigitalharbor.back.ResponseModel;
import jrogetdigitalharbor.back.models.Hospital;
import jrogetdigitalharbor.back.repositories.HospitalRepository;
import jrogetdigitalharbor.back.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController extends BaseController {

    private HospitalRepository repository;
    private UserRepository userRepository;

    public HospitalController(HospitalRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public ResponseModel create(@RequestBody RequestModel<Hospital> request) {
        try {
            userRepository.findById(request.userId).get();
            Hospital newHospital = new Hospital(request);
            Hospital hospitalCreated = repository.save(newHospital);
            return sendResponse("Hospital created.", hospitalCreated);
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @GetMapping("")
    public ResponseModel readAll() {
        List<Hospital> hospitals = this.repository.findAll();
        return sendResponse("hospitals found.", hospitals);
    }

    @GetMapping("/{id}")
    public ResponseModel readOne(@PathVariable String id) {
        Optional<Hospital> doctorFound = repository.findById(id);
        if (!doctorFound.isPresent()) {
            return sendResponse("Hospital not found.");
        }
        return sendResponse("Hospital found.", doctorFound.get());
    }

    @PutMapping("/{id}")
    public ResponseModel update(@RequestBody RequestModel<Hospital> request, @PathVariable String id) {
        try {
            userRepository.findById(request.userId).get();
            Hospital hospitalFound = new Hospital(request);
            return sendResponse("Hospital updated.", this.repository.save(hospitalFound));
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseModel delete(@PathVariable String id) {
        if (!repository.findById(id).isPresent()) {
            return sendResponse("Hospital not found.");
        }
        repository.deleteById(id);
        return sendResponse("Hospital deleted.");
    }
}
