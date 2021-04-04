package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.RequestModel;
import jrogetdigitalharbor.back.ResponseModel;
import jrogetdigitalharbor.back.models.Specialty;
import jrogetdigitalharbor.back.repositories.SpecialtyRepository;
import jrogetdigitalharbor.back.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController extends BaseController {

    private SpecialtyRepository repository;
    private UserRepository userRepository;
    private String modelName = "Specialty";

    public SpecialtyController(SpecialtyRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public ResponseModel create(@RequestBody RequestModel<Specialty> request) {
        try {
            userRepository.findById(request.userId).get();
            Specialty newSpecialty = new Specialty(request);
            Specialty specialtyCreated = repository.save(newSpecialty);
            return sendResponse(modelName + " created.", specialtyCreated);
        } catch (NoSuchElementException e) {
            return sendResponse("User not found.");
        } catch (Exception e) {
            return sendErrorResponse(e);
        }
    }

    @GetMapping("")
    public ResponseModel readAll() {
        List<Specialty> specialties = this.repository.findAll();
        return sendResponse(modelName + " found.", specialties);
    }

    @GetMapping("/{id}")
    public ResponseModel readOne(@PathVariable String id) {
        Optional<Specialty> specialtyFound = repository.findById(id);
        if (!specialtyFound.isPresent()) {
            return sendResponse(modelName + " not found.");
        }
        return sendResponse(modelName + " found.", specialtyFound.get());
    }

    @PutMapping("/{id}")
    public ResponseModel update(@RequestBody RequestModel<Specialty> request, @PathVariable String id) {
        try {
            userRepository.findById(request.userId).get();
            Specialty specialtyFound = new Specialty(request);
            return sendResponse(modelName + " updated.", this.repository.save(specialtyFound));
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
}
