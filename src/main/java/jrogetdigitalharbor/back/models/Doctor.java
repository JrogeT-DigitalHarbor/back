/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrogetdigitalharbor.back.models;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Document(collection = "doctors")
public class Doctor {

    @Id
    public String id;

    public String name;
    public String lastname;
    public Date dateOfBirth;
    public String address;
    public String profilePicture;
    public List<Specialty> specialties;

    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private User userCreator;
    @LastModifiedDate
    private Instant lastModifierDate;
    @LastModifiedBy
    private User userLastModifier;

    public Doctor() {
        this.name = "name";
        this.lastname = "lastname";
        this.dateOfBirth = new Date();
        this.address = "address";
        this.profilePicture = "profilePicture";
        this.specialties = new LinkedList<>();
    }

    public Doctor(String name, String lastname, Date dateOfBirth, String address, String profilePicture, User userCreator) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.profilePicture = profilePicture;
        this.specialties = new LinkedList<>();
        this.userCreator = userCreator;
        this.createdDate = Instant.now();
        this.userLastModifier = userCreator;
        this.lastModifierDate = Instant.now();
    }

    @Override
    public String toString() {
        return this.id + ":" + this.name;
    }

}
