package de.fh_dortmund.swt2.backend.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Estate_seq")
    @SequenceGenerator(name = "Estate_seq", sequenceName = "Estate_seq", allocationSize = 1)
    private Long id;

    //Attribute
    @NotNull(message = "Fläche muss angegeben werden")
    private Double area;
    @NotNull(message = "Zimmeranzahl muss angegeben werden")
    private Double roomCount;
    @NotBlank(message = "Beschreibung darf nicht leer sein")
    private String description;
    @NotNull(message = "Kaltmiete muss angegeben werden")
    private Double rentCold;
    @NotNull(message = "Warmmiete muss angegeben werden")
    private Double rentWarm;
    @NotBlank(message = "Adresse darf nicht leer sein")
    private String adress;
    @ManyToOne
    private AppUser landlord;
    @ManyToMany
    private List<AppUser> tenants = new LinkedList<AppUser>();
    @Column(nullable = false)
    private boolean validated;
    @Column(nullable = false)
    private boolean visible;
    @Column(nullable = false)
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
        // Wird eine Immobilie validiert, ist sie direkt auch sichtbar und verfügbar
        this.validated = validated;
        this.visible = validated;
        this.available = validated;
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
