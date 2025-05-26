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
    private boolean visibility;
    // todo: nummer? passwort? chatliste?
    private LinkedList<Estate> realEstates;
    private LinkedList<Estate> history;
    private LinkedList<Estate> saved;

    // Konstruktoren
    public User() {
    }

    public User(String firstName, String name, LocalDate birthday, String email) {
        // todo: Id setzen
        this.firstName = firstName;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.visibility = true;
        this.realEstates = new LinkedList<Estate>();
        this.history = new LinkedList<Estate>();
        this.saved = new LinkedList<Estate>();
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

    public boolean isVisible() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public LinkedList<Estate> getEstate() {
        return null;
    }

    public void setEstate(LinkedList<Estate> Estate) {
        //Estate.add
    }

    public LinkedList<Estate> getVerlauf() {
        return null;
    }

    public void setVerlauf(LinkedList<Estate> verlauf) {
        //this.verlauf = verlauf;
    }

    public LinkedList<Estate> getGespeichert() {
        return null;
    }

    public void setGespeichert(LinkedList<Estate> gespeichert) {
        //this.gespeichert = gespeichert;
    }

}
