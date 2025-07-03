package de.fh_dortmund.swt2.backend.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.*;  // Für Entity, Id, Column, usw.
import jakarta.persistence.CascadeType;              
import jakarta.validation.constraints.Pattern;  // Für Validierung der Telefonnummer
import jakarta.validation.constraints.Email; 
import java.io.*;

@Entity
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AppUser_seq")
    @SequenceGenerator(name = "AppUser_seq", sequenceName = "AppUser_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String name;
    private LocalDate dob;
    @Column(unique = true)
    @Email(message = "Ungültige E-Mail-Adresse")
    private String email;
    private String gender;
    @Pattern(regexp = "^\\+?[0-9 ]{7,20}$", message = "Ungültige Telefonnummer")
    private String phoneNumber;
    private String password;
    private boolean visible;
    
    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL)
    private List<Estate> realEstates = new LinkedList<Estate>();
    @ManyToMany
    private List<Estate> history = new LinkedList<Estate>();
    @ManyToMany
    private List<Estate> saved = new LinkedList<Estate>();
    // TODO: chatliste?


    // Konstruktoren
    public AppUser() {}

    public AppUser(String firstName, String name, LocalDate dob, String email, String gender, String phonenumber) {
        this.firstName = firstName;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phonenumber;
        this.visible = true;
    }
    

    // Methoden
    public void addRealEstate(Estate estate) {
        this.realEstates.add(estate);
    }

    public void removeRealEstate(Estate estate) {
        this.realEstates.remove(estate);
    }

    public void addToHistory(Estate estate) {
        this.history.add(estate);
    }

    public void removeFromHistory(Estate estate) {
        this.history.remove(estate);
    }

    public void saveEstate(Estate estate) {
        this.saved.add(estate);
    }

    public void removeSavedEstate(Estate estate) {
        this.saved.remove(estate);
    }


    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {        
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Estate> getRealEstates() {
        return realEstates;
    }

    public void setRealEstates(List<Estate> realEstates) {
        this.realEstates = realEstates;
    }

    public List<Estate> getHistory() {
        return history;
    }

    public void setHistory(List<Estate> history) {
        this.history = history;
    }

    public List<Estate> getSaved() {
        return saved;
    }

    public void setSaved(List<Estate> saved) {
        this.saved = saved;
    }
}

