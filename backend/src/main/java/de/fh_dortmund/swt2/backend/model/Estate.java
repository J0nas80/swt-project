package de.fh_dortmund.swt2.backend.model;

import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Estate {
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
    private boolean validated;
    private boolean visible;
    private boolean available;


    //Konstruktoren
    public Estate(){}

    public Estate(double area, double roomCount, String description, double rentCold, double rentWarm, String adress, User landLord){
        this.area = area;
        this.roomCount = roomCount;
        this.description = description;
        this.rentCold = rentCold;
        this.rentWarm = rentWarm;
        this.adress = adress;
        this.landlord = landLord;
        this.tenants = new LinkedList<User>();
        validated = false;
        visible = false;
        available = false;
    }


    // Methoden
    public void addTenant(User tenant){
        this.tenants.add(tenant);
    }

    public void removeTenant(User tenant){
        this.tenants.remove(tenant);
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

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public LinkedList<User> getTenants() {
        return tenants;
    }

    public void setTenants(LinkedList<User> tenants) {
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
