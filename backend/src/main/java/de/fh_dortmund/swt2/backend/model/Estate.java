package de.fh_dortmund.swt2.backend.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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

@Entity
public class Estate {

    // Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Estate_seq")
    @SequenceGenerator(name = "Estate_seq", sequenceName = "Estate_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "Wohnform muss angegeben werden")
    private String type;

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

    @NotNull(message = "Adresse darf nicht leer sein")
    @Embedded
    private Address address;

    @ManyToOne
    private AppUser landlord;

    @NotNull(message = "Pfad für Bild muss angegeben werden")
    private String img;

    @ManyToMany
    private List<AppUser> tenants = new LinkedList<AppUser>();

    @Column(nullable = false)
    private boolean validated;

    @Column(nullable = false)
    private boolean visible;

    @Column(nullable = false)
    private LocalDate availableFrom;

    // Konstruktoren
    public Estate() {
    }

    public Estate(String type, double area, double roomCount, String description, double rentCold, double rentWarm,
            Address address,
            AppUser landLord, String img, LocalDate availableFrom) {
        this.type = type;
        this.area = area;
        this.roomCount = roomCount;
        this.description = description;
        this.rentCold = rentCold;
        this.rentWarm = rentWarm;
        this.address = address;
        this.landlord = landLord;
        this.validated = false;
        this.visible = false;
        //Img über Base64
        this.img = img;
        this.availableFrom = availableFrom;
    }

    // Methoden
    // Muster: Fabrikmethode, implementiert als statische Methode zur Erzeugung von
    // Objekten
    public static Estate createEstate(String type, double area, double roomCount, String description, double rentCold,
            double rentWarm, Address address,
            AppUser landLord, String img, LocalDate availableFrom) {
        Estate estate = new Estate(type, area, roomCount, description, rentCold, rentWarm, address, landLord, img,
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

    public void addTenants(AppUser tenant) {
        tenants.add(tenant);
    }

    public void removeTenants(AppUser tenant) {
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
        // Wird eine Immobilie validiert, ist sie direkt auch sichtbar und verfügbar
        this.validated = validated;
        this.visible = validated;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LocalDate isAvailable() {
        return availableFrom;
    }

    public void setAvailable(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }
}
