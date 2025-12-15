package com.ecom.Ecom.inheritanceTypes.singleTableClass;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class Mentor extends User {

    private double rating;
}
