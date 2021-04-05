package jrogetdigitalharbor.back.models;

import jrogetdigitalharbor.back.RequestModel;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

public class DoctorDTO {

    public String id;
    public String name;
    public String lastname;
    public Instant dateOfBirth;
    public String address;
    public String profilePicture;
    public List<String> specialtiesIds;
    public String hospitalId;
    public Instant createdDate;
    public String userCreatorId;
    public Instant lastModifierDate;
    public String userLastModifierId;
    public List<AppointmentDTO> appointments;

    public DoctorDTO() {
    }

    public DoctorDTO(Doctor request, List<AppointmentDTO> appointments) {
        this.id = request.id;
        this.name = request.name;
        this.lastname = request.lastname;
        this.dateOfBirth = request.dateOfBirth;
        this.address = request.address;
        this.profilePicture = request.profilePicture;
        this.specialtiesIds = request.specialtiesIds;
        this.hospitalId = request.hospitalId;
        this.userCreatorId = request.userCreatorId;
        this.appointments = appointments;
        this.createdDate = request.createdDate;
        this.userCreatorId = request.userCreatorId;
        this.createdDate = request.createdDate;
        this.userLastModifierId = request.userLastModifierId;
        this.lastModifierDate = request.lastModifierDate;
    }
}
