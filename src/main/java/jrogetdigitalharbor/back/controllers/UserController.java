package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.ResponseModel;
import jrogetdigitalharbor.back.models.User;
import jrogetdigitalharbor.back.repositories.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public ResponseModel create(@RequestBody User newEmployee) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPw = bCryptPasswordEncoder.encode(newEmployee.password);
            newEmployee.password = encodedPw;
            User userCreated = repository.save(newEmployee);
            return sendResponse("User created.", userCreated);
        } catch (Exception e) {
            return sendResponse(e.getMessage(), newEmployee);
        }
    }

    @GetMapping("")
    public ResponseModel readAll() {
        List<User> users = this.repository.findAll();
        return sendResponse("Users found.", users);
    }

    @GetMapping("/{id}")
    public ResponseModel readOne(@PathVariable String id) {
        Optional<User> userFound = repository.findById(id);
        if (!userFound.isPresent()) {
            return sendResponse("User not found.");
        }
        return sendResponse("User found.", userFound.get());
    }

    @PutMapping("/{id}")
    public ResponseModel update(@RequestBody User newUser, @PathVariable String id) {
        Optional<User> found = repository.findById(id);
        if (!found.isPresent()) {
            return sendResponse("User not found");
        }
        try {
            User userFound = found.get();
            userFound.username = newUser.username;
            return sendResponse("User updated.", repository.save(userFound));
        } catch (Exception e) {
            return sendResponse(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseModel delete(@PathVariable String id) {
        if (!repository.findById(id).isPresent()) {
            return sendResponse("User not found.");
        }
        repository.deleteById(id);
        return sendResponse("User deleted.");
    }

    @PostMapping("login")
    public ResponseModel login(@RequestBody User user) {
        try {
            User example = new User(user.username, null);
            Optional<User> posibleUserFound = repository.findOne(Example.of(example));
            if (!posibleUserFound.isPresent()) {
                return sendResponse("username not exist", 1);
            }
            User userFound = posibleUserFound.get();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (!bCryptPasswordEncoder.matches(user.password, userFound.password)) {
                return sendResponse("incorrect password", 2);
            }
            return sendResponse("Login ok", userFound);
        } catch (Exception e) {
            return sendResponse(e.getMessage(), user);
        }
    }
}
