package com.annuaire.stage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "annuaires")
public class Annuaire implements Serializable {
    @Id
    @GeneratedValue(generator = "annuaire_generator")
    @SequenceGenerator(
            name = "annuaire_generator",
            sequenceName = "annuaire_sequence",
            initialValue = 1000
    )
    private Long id;
    
    @ApiModelProperty(notes = "Provided user name", required =true)
    @NotBlank
    private String nom;
    
    @ApiModelProperty(notes = "Provided user prenom", required =true)
    @NotBlank
    private String prenom;
    
    @ApiModelProperty(notes = "Provided user departement", required =true)
    @NotBlank
    private String departement;

    @ApiModelProperty(notes = "Provided user phone", required =true)
    @NotBlank
    private String phone;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    

        
	
}
