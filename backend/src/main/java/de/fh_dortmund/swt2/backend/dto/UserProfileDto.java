package de.fh_dortmund.swt2.backend.dto;

import java.time.LocalDate;

import de.fh_dortmund.swt2.backend.model.AppUser;

public class UserProfileDto {

    // Attribute
    private String firstName;
    private String name;
    private LocalDate dob;
    private String email;
    private String gender;
    private String phoneNumber;

    // Konstruktoren
    public UserProfileDto(AppUser user){
        this.firstName = user.getFirstName();
        this.name = user.getName();
        this.dob = user.getDob();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
    }

    // Getter und Setter
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhonenumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
