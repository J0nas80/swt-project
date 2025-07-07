package de.fh_dortmund.swt2.backend.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.*;

@Entity
public class Estate implements Serializable{

    // Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Estate_seq")
    @SequenceGenerator(name = "Estate_seq", sequenceName = "Estate_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Titel darf nicht leer sein")
    private String titel;

    @NotNull(message = "Wohnform darf nicht null sein")
    private String type;

    @NotNull(message = "Fläche darf nicht null sein")
    private Double area;

    @NotNull(message = "Zimmeranzahl darf nicht null sein")
    private Double roomCount;

    @NotNull(message = "Beschreibung darf nicht null sein")
    private String description;

    @NotNull(message = "Kaltmiete darf nicht null sein")
    private Double rentCold;

    @NotNull(message = "Adresse darf nicht null sein")
    @Embedded
    private Address address;

    @ManyToOne
    @JsonBackReference
    private AppUser landlord;

    @NotNull(message = "Bild darf nicht null sein")
    private String img; // Base64

    @ManyToMany
    @JsonIgnore
    private List<AppUser> tenants = new LinkedList<AppUser>();

    @Column(nullable = false)
    private boolean validated;

    @Column(nullable = false)
    private boolean visible;

    @NotNull(message = "Verfügbarkeitsdatum darf nicht null sein")
    private LocalDate availableFrom;

    // Konstruktoren
    public Estate() {
    }

    public Estate(String titel, String type, double area, double roomCount, String description, double rentCold,
            Address address,
            AppUser landLord, String img, LocalDate availableFrom) {
        this.titel = titel;
        this.type = type;
        this.area = area;
        this.roomCount = roomCount;
        this.description = description;
        this.rentCold = rentCold;
        this.address = address;
        this.landlord = landLord;
        this.validated = false;
        this.visible = false;
        this.img = img;
        this.availableFrom = availableFrom;
    }

    // Methoden

    // Muster: Fabrikmethode, implementiert als statische Methode zur Erzeugung von
    // Objekten
    public static Estate createEstate(String titel, String type, double area, double roomCount, String description,
            double rentCold,
            double rentWarm, Address address,
            AppUser landLord, String img, LocalDate availableFrom) {
        Estate estate = new Estate(titel, type, area, roomCount, description, rentCold, address, landLord,
                img,
                availableFrom);
        estate.setValidated(false);
        estate.setVisible(false);
        return estate;
    }

    public void addTenant(AppUser tenant) {
        this.tenants.add(tenant);
    }

    public void removeTenant(AppUser tenant) {
        this.tenants.remove(tenant);
    }

    
    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitel(){
        return this.titel;
    }

    public void setTitel(String titel){
        this.titel = titel;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
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

    public Address getAddress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
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
        // Wird eine Immobilie validiert, wird sie auch sichtbar
        this.validated = validated;
        this.visible = validated;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }
}
