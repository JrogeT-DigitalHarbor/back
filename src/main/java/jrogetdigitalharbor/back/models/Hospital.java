/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrogetdigitalharbor.back.models;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Document("hospitals")
public class Hospital {

    @Id
    public String id;

    @NotNull
    @NotEmpty
    @Min(5)
    @Max(30)
    public String name;

    @NotNull
    @NotEmpty
    @Min(5)
    @Max(30)
    public String description;

    @NotNull
    @NotEmpty
    public String doctorId;

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
