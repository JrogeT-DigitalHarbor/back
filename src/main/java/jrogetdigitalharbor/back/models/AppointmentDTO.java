/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrogetdigitalharbor.back.models;

import jrogetdigitalharbor.back.RequestModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Document("appointments")
public class AppointmentDTO {

    public String description;
    public Instant date;
    public String doctorId;
    public Instant createdDate;
    public String userCreatorId;
    public Instant lastModifierDate;
    public String userLastModifierId;
    public Patient patient;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Appointment request, Patient patient) {
        this.description = request.description;
        this.date = request.date;
        this.doctorId = request.doctorId;
        this.userCreatorId = request.userCreatorId;
        this.createdDate = request.createdDate;
        this.patient = patient;
        this.userCreatorId = request.userCreatorId;
        this.createdDate = request.createdDate;
        this.userLastModifierId = request.userLastModifierId;
        this.lastModifierDate = request.lastModifierDate;
    }
}
