package de.fh_dortmund.swt2.backend.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;                // Für Entity, Id, Column, usw.
import jakarta.validation.constraints.Pattern;  // Für Validierung der Telefonnummer
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class AppUser {

    //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AppUser_seq")
    @SequenceGenerator(name = "AppUser_seq", sequenceName = "AppUser_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Vorname darf nicht leer sein")
    private String firstName;

    @NotBlank(message = "Nachname darf nicht leer sein")
    private String name;

    @NotNull(message = "Geburtsdatum darf nicht null sein")
    private LocalDate dob;

    @NotBlank(message = "E-Mail darf nicht leer sein")
    @Email(message = "Ungültige E-Mail-Adresse")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Geschlecht darf nicht leer sein")
    private String gender;

    @NotBlank(message = "Telefonnummer darf nicht leer sein")
    @Pattern(regexp = "^\\+?[0-9 ]{7,20}$", message = "Ungültige Telefonnummer")
    @Column(unique = true)
    private String phonenumber;

    @NotBlank(message = "Passwort darf nicht leer sein")
    @JsonIgnore
    private String password;

    @Column(nullable = false)
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

    public AppUser(String firstName, String name, LocalDate dob, String email, String gender, String phonenumber, String password) {
        this.firstName = firstName;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.password = password;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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