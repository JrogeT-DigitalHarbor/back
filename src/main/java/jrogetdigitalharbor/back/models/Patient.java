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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "patients")
public class Patient {

    @Id
    public String id;

    @NotNull
    @NotEmpty
    @Min(1)
    @Max(20)
    public String name;

    @NotNull
    @NotEmpty
    @Min(1)
    @Max(20)
    public String lastname;

    @NotNull
    @NotEmpty
    public Date dateOfBirth;

    @NotNull
    @NotEmpty
    @Min(5)
    @Max(30)
    public String address;

    @NotNull
    @NotEmpty
    public String profilePicture;

    @NotNull
    public List<Appoinment> appointments;

    @NotNull
    @NotEmpty
    @CreatedDate
    public Instant createdDate;

    @NotNull
    @NotEmpty
    @CreatedBy
    public User userCreator;

    @NotNull
    @NotEmpty
    @LastModifiedDate
    public Instant lastModifierDate;

    @NotNull
    @NotEmpty
    @LastModifiedBy
    public User userLastModifier;

}
