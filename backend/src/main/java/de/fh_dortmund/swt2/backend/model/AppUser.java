package de.fh_dortmund.swt2.backend.model;

import java.time.LocalDate;
import jakarta.persistence.*;                // Für Entity, Id, Column, usw.
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
    private String phonenumber;
    private String password;
    private boolean visible;
    
    // Konstruktoren
    public AppUser() {
    }

    public AppUser(String firstName, String name, LocalDate dob, String email, String gender, String phonenumber) {
    this.firstName = firstName;
    this.name = name;
    this.dob = dob;
    this.email = email;
    this.gender = gender;
    this.phonenumber = phonenumber;
    this.visible = true;
}


    public Long getId() {
    return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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


}
