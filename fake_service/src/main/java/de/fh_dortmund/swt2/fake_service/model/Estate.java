package de.fh_dortmund.swt2.fake_service.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private AppUser landlord;
    @ManyToMany
    private List<AppUser> tenants = new LinkedList<AppUser>();
    private boolean validated;
    private boolean visible;
    private boolean available;


    //Konstruktoren
    public Estate(){}

    public Estate(double area, double roomCount, String description, double rentCold, double rentWarm, String adress, AppUser landLord){
        this.area = area;
        this.roomCount = roomCount;
        this.description = description;
        this.rentCold = rentCold;
        this.rentWarm = rentWarm;
        this.adress = adress;
        this.landlord = landLord;
        validated = false;
        visible = false;
        available = false;
    }


    // Methoden
    public void addTenant(AppUser tenant){
        this.tenants.add(tenant);
    }

    public void removeTenant(AppUser tenant){
        this.tenants.remove(tenant);
    }


    //Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addTenants(AppUser tenant){
        tenants.add(tenant);
    }

    public void removeTenants(AppUser tenant){
        tenants.remove(tenant);
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(double roomCount) {
        this.roomCount = roomCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRentCold() {
        return rentCold;
    }

    public void setRentCold(double rentCold) {
        this.rentCold = rentCold;
    }

    public double getRentWarm() {
        return rentWarm;
    }

    public void setRentWarm(double rentWarm) {
        this.rentWarm = rentWarm;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public AppUser getLandlord() {
        return landlord;
    }

    public void setLandlord(AppUser landlord) {
        this.landlord = landlord;
    }

    public List<AppUser> getTenants() {
        return tenants;
    }

    public void setTenants(List<AppUser> tenants) {
        this.tenants = tenants;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
        // TODO: evtl. bei Validierung auch direkt sichtbar und verfügbar machen?
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
