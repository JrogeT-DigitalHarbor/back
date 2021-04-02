/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrogetdigitalharbor.back.models;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
public class Patient {

    @Id
    public String id;

    public String name;
    public String lastname;
    public Date dateOfBirth;
    public String address;
    public String profilePicture;
    public List<Appoinment> appointments;

    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private User userCreator;
    @LastModifiedDate
    private Instant lastModifierDate;
    @LastModifiedBy
    private User userLastModifier;

    public Patient() {
        this.name = "name";
        this.lastname = "lastname";
        this.dateOfBirth = new Date();
        this.address = "address";
        this.profilePicture = "profilePicture";
        this.appointments = new LinkedList<>();
    }

    public Patient(String name, String lastname, Date dateOfBirth, String address, String profilePicture, User userCreator) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.profilePicture = profilePicture;
        this.appointments = new LinkedList<>();
        this.createdDate = Instant.now();
        this.userCreator = userCreator;
        this.lastModifierDate= Instant.now();
        this.userLastModifier = userCreator;
    }

    @Override
    public String toString() {
        return this.id + ":" + this.name;
    }

}
