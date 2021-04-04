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

@Document(collection = "specialties")
public class Specialty {

    @Id
    public String id;

    @NotNull
    @NotEmpty
    @Min(1)
    @Max(20)
    public String name;

    @NotNull
    @NotEmpty
    @Min(5)
    @Max(30)
    public String description;

    @NotNull
    @NotEmpty
    public String icon;

    @NotNull
    @NotEmpty
    @CreatedDate
    private Instant createdDate;

    @NotNull
    @NotEmpty
    @CreatedBy
    private User userCreator;

    @NotNull
    @NotEmpty
    @LastModifiedDate
    private Instant lastModifierDate;

    @NotNull
    @NotEmpty
    @LastModifiedBy
    private User userLastModifier;
}
