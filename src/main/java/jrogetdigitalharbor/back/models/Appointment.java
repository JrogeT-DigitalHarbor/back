/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrogetdigitalharbor.back.models;

import jrogetdigitalharbor.back.RequestModel;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Document("appointments")
public class Appointment {

    @NotNull
    @NotEmpty
    public String description;

    @NotNull
    public Instant date;

    @NotNull
    @NotEmpty
    public String doctorId;

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

    public Appointment() {
    }

    public Appointment(RequestModel<Appointment> request) {
        this.description = request.body.description;
        this.date = request.body.date;
        this.doctorId = request.body.doctorId;
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
