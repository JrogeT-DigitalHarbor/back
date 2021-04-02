/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.models.Patient;
import jrogetdigitalharbor.back.models.User;
import jrogetdigitalharbor.back.repositories.PatientRepository;
import jrogetdigitalharbor.back.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public User create(@RequestBody User newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("")
    public List<User> readAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public User readOne(@PathVariable String id) {
        Optional<User> userFound = repository.findById(id);
        if (!userFound.isPresent()) {
            return null;
        }
        return userFound.get();
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User newUser, @PathVariable String id) {
        Optional<User> found = repository.findById(id);
        if (!found.isPresent()) {
            return null;
        }
        User userFound = found.get();
        userFound.username = newUser.username;
        return repository.save(userFound);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable String id) {
        repository.deleteById(id);
    }
}
