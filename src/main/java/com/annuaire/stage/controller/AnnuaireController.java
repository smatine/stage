package com.annuaire.stage.controller;

import com.annuaire.stage.exception.ResourceNotFoundException;
import com.annuaire.stage.model.Annuaire;
import com.annuaire.stage.repository.AnnuaireRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import io.swagger.annotations.Authorization;

@RestController
@Api(value = "user", tags = {"User API, Rest API for user operations"})
public class AnnuaireController {

    @Autowired
    private AnnuaireRepository annuaireRepository;
    
    
    @GetMapping("/annuaires")
    @PreAuthorize("hasRole('ROLE_USER')")
    /*@ApiOperation(value = "\"Display all user", authorizations = { @Authorization(value="apiKey") }, response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )*/

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
    
    @GetMapping(path = "/annuaires/search/{nom}")
    List<Annuaire> search(@PathVariable String nom) {
    	List<Annuaire> annuaires = annuaireRepository.findByNom(nom);
        return annuaires;
    }
   @GetMapping(path = "/annuaires/search/{nom}/{prenom}")
    List<Annuaire> search(@PathVariable String nom, @PathVariable String prenom) {
    	List<Annuaire> annuaires = annuaireRepository.findByNomAndPrenom(nom, prenom);
        return annuaires;
    }
   
    @GetMapping(path = "/annuaires/search/{nom}/{prenom}/{departement}")
    List<Annuaire> search(@PathVariable String nom, @PathVariable String prenom, @PathVariable String departement) {
    	List<Annuaire> annuaires = annuaireRepository.findByNomAndPrenomAndDepartement(nom, prenom, departement);
        return annuaires;
    }
    
    @PostMapping("/annuaires")
    public Annuaire addAnnuaire(@Valid @RequestBody Annuaire annuaire) {
    	return annuaireRepository.save(annuaire);
    }
    
    @PutMapping("/annuaires/{annuaireId}")
    public Annuaire updateAnnuaire(@PathVariable Long annuaireId,
                               @Valid @RequestBody Annuaire annuaireRequest) {
    	
    	
        return annuaireRepository.findById(annuaireId)
                .map(annuaire -> {
                	      
                	annuaire.setNom(annuaireRequest.getNom());
                	annuaire.setPrenom(annuaireRequest.getPrenom());
                	annuaire.setDepartement(annuaireRequest.getDepartement());
                	annuaire.setPhone(annuaireRequest.getPhone());
                	
                	
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
