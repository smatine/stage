package com.annuaire.stage;

import com.annuaire.stage.exception.ResourceNotFoundException;

import com.annuaire.stage.AnnuaireRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class AnnuaireController {

    @Autowired
    private AnnuaireRepository annuaireRepository;
    
    
    @GetMapping("/annuaires")
    Collection<Annuaire> annuaires() {
    	Collection<Annuaire> annuaires = annuaireRepository.findAll();
        return annuaires;
    }
    
    @GetMapping("/annuaires/{id}")
    ResponseEntity<?> getAnnuaire(@PathVariable Long id) {
        Optional<Annuaire> annuaire = annuaireRepository.findById(id);
        
        return annuaire.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/annuaire")
    public Annuaire addAnnuaire(@Valid @RequestBody Annuaire annuaire) {
    	return annuaireRepository.save(annuaire);
    }
    
    @PutMapping("/annuaires/{annuaireId}")
    public Annuaire updateSubnet(@PathVariable Long annuaireId,
                               @Valid @RequestBody Annuaire annuaireRequest) {
    	
    	
        return annuaireRepository.findById(annuaireId)
                .map(annuaire -> {
                	      
                	annuaire.setNom(annuaireRequest.getNom());
                	annuaire.setPrenom(annuaireRequest.getPrenom());
                	annuaire.setDepartement(annuaireRequest.getDepartement());
                	
                	
                    return annuaireRepository.save(annuaire);
                }).orElseThrow(() -> new ResourceNotFoundException("Annuaire not found with id " + annuaireId));
    }

    @DeleteMapping("/annuaires/{annuaireId}")
    public ResponseEntity<?> deleteAnnuaire(@PathVariable Long annuaireId) {

        return annuaireRepository.findById(annuaireId)
                .map(annuaire -> {               	
                	
                	annuaireRepository.delete(annuaire);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Annuaire not found with id " + annuaireId));

    }
}
