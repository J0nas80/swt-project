package de.fh_dortmund.swt2.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.fh_dortmund.swt2.backend.model.Estate;
import de.fh_dortmund.swt2.backend.model.User;
import de.fh_dortmund.swt2.backend.service.EstateService;

@RestController
@RequestMapping("api/estate")
public class EstateController {
    
    private final EstateService estateService;

    public EstateController(EstateService estateService){
        this.estateService = estateService;
    }


    @PostMapping()
    public ResponseEntity<?> saveEstate(@RequestBody Estate estate){
        estateService.saveEstate(estate);
        return ResponseEntity.ok().build();
    }

    /* TODO: Nochmal nachfragen, was soll die Anfrage?
    @GetMapping
    public ResponseEntity<?> getEstate( ... ){
        return null;
    } */

    // Gibt aktuell alle Estates zurück
    // TODO: Stattdessen zb. nur eine begrenzte Anzahl und nur die wichtigen Attribute?
    @GetMapping("/all")
    public ResponseEntity<?> getEstates(){
        List<Estate> estates = estateService.getAllEstates();
        return ResponseEntity.ok(estates);
    }
    
}
