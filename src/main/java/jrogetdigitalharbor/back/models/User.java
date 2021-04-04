package jrogetdigitalharbor.back.models;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document("users")
public class User {

    @Id
    public String id;

    @NotNull
    @NotEmpty
    public String username;

    @NotNull
    @NotEmpty
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
