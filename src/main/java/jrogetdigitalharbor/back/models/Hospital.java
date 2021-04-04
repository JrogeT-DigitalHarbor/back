/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrogetdigitalharbor.back.models;

import jrogetdigitalharbor.back.RequestModel;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Document("hospitals")
public class Hospital {

    @Id
    public String id;

    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    public List<String> doctorsIds;

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

    public Hospital() {
    }

    public Hospital(RequestModel<Hospital> request) {
        this.id = request.body.id;
        this.name = request.body.name;
        this.doctorsIds = request.body.doctorsIds;
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
