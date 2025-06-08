package de.fh_dortmund.swt2.backend.model;

import java.time.LocalDate;
import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String name;
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
    private boolean visible;
    // TODO: passwort? chatliste?
    private LinkedList<Estate> realEstates;
    private LinkedList<Estate> history;
    private LinkedList<Estate> saved;


    // Konstruktoren
    public User() {}

    public User(String firstName, String name, LocalDate birthday, String email, String phoneNumber) {
        this.firstName = firstName;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.visible = true;
        this.realEstates = new LinkedList<Estate>();
        this.history = new LinkedList<Estate>();
        this.saved = new LinkedList<Estate>();
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LinkedList<Estate> getRealEstates() {
        return realEstates;
    }

    public void setRealEstates(LinkedList<Estate> realEstates) {
        this.realEstates = realEstates;
    }

    public LinkedList<Estate> getHistory() {
        return history;
    }

    public void setHistory(LinkedList<Estate> history) {
        this.history = history;
    }

    public LinkedList<Estate> getSaved() {
        return saved;
    }

    public void setSaved(LinkedList<Estate> saved) {
        this.saved = saved;
    }
}
