package com.ecom.Ecom.inheritanceTypes.tablePerClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_Instructor")
public class Instructor extends User {

    private double favStudent;
}
