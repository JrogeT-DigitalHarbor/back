/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrogetdigitalharbor.back.models;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("appointments")
public class Appoinment {

    @Id
    public String id;

    public String name;
    public String description;
    public String doctorId;

    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private User userCreator;
    @LastModifiedDate
    private Instant lastModifierDate;
    @LastModifiedBy
    private User userLastModifier;

    public Appoinment(String name, String description, String doctorId, User userCreator) {
        this.name = name;
        this.description = description;
        this.doctorId = doctorId;
        this.createdDate = Instant.now();
        this.userCreator = userCreator;
        this.lastModifierDate = Instant.now();
        this.userLastModifier = userCreator;
    }

    public void update(String name, String description, String doctorId, User userLastModifier) {
        this.name = name;
        this.description = description;
        this.doctorId = doctorId;
        this.lastModifierDate = Instant.now();
        this.userLastModifier = userLastModifier;
    }
}
