package de.fh_dortmund.swt2.backend.factory;

import java.time.LocalDate;

import de.fh_dortmund.swt2.backend.model.Address;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.model.Estate;

public class EstateFactory {
        
    // Muster: Fabrikmethode, implementiert als statische Methode zur Erzeugung von
    // Objekten
    public static Estate createEstate(String titel, String type, double area, double roomCount, String description,
            double rentCold,
            double rentWarm, Address address,
            AppUser landLord, String img, LocalDate availableFrom) {
        Estate estate = new Estate(titel, type, area, roomCount, description, rentCold, address, landLord,
                img,
                availableFrom);
        estate.setValidated(false);
        estate.setVisible(false);
        return estate;
    }
}
