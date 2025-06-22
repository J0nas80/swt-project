package de.fh_dortmund.swt2.backend.model;


import java.time.LocalDate;
import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.*;

@Entity
public class Estate implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Attribute
    private double area;
    private double roomCount;
    private String description;
    private double rentCold;
    private double rentWarm;
    private String adress; //muss man vllt noch anpassen, wegen DB Datentyp?
    private User landlord;
    private LinkedList<User> tenants;

    //Konstruktoren
    public Estate(){

    }
    public Estate(double area, double roomCount, String description, double rentCold, double rentWarm, String adress, User landLord){
        this.area = area;
        this.roomCount = roomCount;
        this.description = description;
        this.rentCold = rentCold;
        this.rentWarm = rentWarm;
        this.adress = adress;
        this.landlord = landLord;
        this.tenants = new LinkedList<User>();
    }

    //Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addTenants(User tenant){
        tenants.add(tenant);
    }

    public void removeTenants(User tenant){
        tenants.remove(tenant);
    }

    //hier theoretischen Methoden zum freigeben, deaktivieren usw. 

}
