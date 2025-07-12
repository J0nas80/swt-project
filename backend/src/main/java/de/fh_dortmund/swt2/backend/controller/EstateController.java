package de.fh_dortmund.swt2.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.fh_dortmund.swt2.backend.dto.EstateCreateDto;
import de.fh_dortmund.swt2.backend.model.Estate;
import de.fh_dortmund.swt2.backend.service.EstateService;

@RestController
@RequestMapping("api/estate")
public class EstateController {
    
    private final EstateService estateService;

    public EstateController(EstateService estateService){
        this.estateService = estateService;
    }


    @PostMapping()
    public ResponseEntity<?> saveEstate(@RequestHeader("Authorization") String token, @ModelAttribute EstateCreateDto estateDto){
        if(token.startsWith("Bearer ")){
            // Token kommt als "Bearer <token>", also muss "Bearer " abgeschnitten werden
            token = token.replace("Bearer ", "");
        }
        // Weiterreichen an Service
        return ResponseEntity.ok(estateService.saveEstate(estateDto, token));
    }

    // Gibt aktuell alle Estates zurück
    // TODO: Stattdessen zb. nur eine begrenzte Anzahl und nur die wichtigen Attribute?
    @GetMapping("/all")
    public ResponseEntity<?> getEstates(){
        List<Estate> estates = estateService.getAllEstates();
        return ResponseEntity.ok(estates);
    }

    //Filtered Inserate Search
    @GetMapping
    public ResponseEntity<?> searchEstates(
        @RequestParam(required = false) String city,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        @RequestParam(required = false) String type
    ){
        List<Estate> result = estateService.searchEstateByFilters(city, minPrice, maxPrice, type);
        return ResponseEntity.ok(result);
    }

    //Get Single Inserat
    @GetMapping("/{id}")
    public ResponseEntity<?> getEstateById(@PathVariable Long id){
        Estate estate = estateService.getEstateById(id);
        if(estate != null){
            return ResponseEntity.ok(estate);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
}
