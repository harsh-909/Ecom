package com.ecom.Ecom.inheritanceTypes.singleTableClass;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("2")
public class Instructor extends User {

    private double favStudent;
}
