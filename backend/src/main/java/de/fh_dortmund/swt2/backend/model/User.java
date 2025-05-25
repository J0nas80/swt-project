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
    private boolean visible;
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
        this.visible = true;
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

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(LocalDate geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSichtbar() {
        return sichtbar;
    }

    public void setSichtbar(boolean sichtbar) {
        this.sichtbar = sichtbar;
    }

    public LinkedList<Estate> getImmobilien() {
        return immobilien;
    }

    public void setImmobilien(LinkedList<Estate> immobilien) {
        this.immobilien = immobilien;
    }

    public LinkedList<Estate> getVerlauf() {
        return verlauf;
    }

    public void setVerlauf(LinkedList<Estate> verlauf) {
        this.verlauf = verlauf;
    }

    public LinkedList<Estate> getGespeichert() {
        return gespeichert;
    }

    public void setGespeichert(LinkedList<Estate> gespeichert) {
        this.gespeichert = gespeichert;
    }

}
