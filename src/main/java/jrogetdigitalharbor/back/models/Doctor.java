package jrogetdigitalharbor.back.models;

import jrogetdigitalharbor.back.RequestModel;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Document(collection = "doctors")
public class Doctor {

    @Id
    public String id;

    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    public String lastname;

    @NotNull
    public Instant dateOfBirth;

    @NotNull
    @NotEmpty
    public String address;

    @NotNull
    @NotEmpty
    public String profilePicture;

    @NotNull
    public List<String> specialtiesIds;

    @NotNull
    @CreatedDate
    public Instant createdDate;

    @NotNull
    @CreatedBy
    public String userCreatorId;

    @NotNull
    @LastModifiedDate
    public Instant lastModifierDate;

    @NotNull
    @LastModifiedBy
    public String userLastModifierId;

    public Doctor() {
    }

    public Doctor(RequestModel<Doctor> request) {
        this.id = request.body.id;
        this.name = request.body.name;
        this.lastname = request.body.lastname;
        this.dateOfBirth = request.body.dateOfBirth;
        this.address = request.body.address;
        this.profilePicture = request.body.profilePicture;
        this.specialtiesIds = request.body.specialtiesIds;
        this.userCreatorId = request.body.userCreatorId;
        this.createdDate = request.body.createdDate;
        if (request.body.userCreatorId == null) {
            this.userCreatorId = request.userId;
            this.createdDate = Instant.now();
        }
        this.userLastModifierId = request.userId;
        this.lastModifierDate = Instant.now();
    }
}
