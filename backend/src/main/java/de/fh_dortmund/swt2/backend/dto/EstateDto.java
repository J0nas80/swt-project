package de.fh_dortmund.swt2.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Die Klasse dient zur Datenübertragung beim Erstellen eines neuen Estates
 * Danach kann aus den Daten erst ein Address-Objekt erstellt werden und dann ein Estate-Objekt
 * (Für das landlord-Attribut muss man über den Token erst den passenden AppUser finden)
 */

public class EstateDto {

    // Estate Attribute
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

    // Address Attribute
    @NotBlank(message = "Straße darf nicht leer sein")
    private String street;

    @NotBlank(message = "Hausnummer darf nicht leer sein")
    private String houseNumber;

    @NotBlank(message = "Postleitzahl darf nicht leer sein")
    private String postalCode;

    @NotBlank(message = "Stadt darf nicht leer sein")
    private String city;

    @NotBlank(message = "Land darf nicht leer sein")
    private String country;

    // Getter & Setter

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Double roomCount) {
        this.roomCount = roomCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRentCold() {
        return rentCold;
    }

    public void setRentCold(Double rentCold) {
        this.rentCold = rentCold;
    }

    public Double getRentWarm() {
        return rentWarm;
    }

    public void setRentWarm(Double rentWarm) {
        this.rentWarm = rentWarm;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
