package com.annuaire.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.annuaire.stage.model.Annuaire;

import java.util.List;

@Repository
public interface AnnuaireRepository extends JpaRepository<Annuaire, Long> {
   List<Annuaire> findByNom(String nom);
   List<Annuaire> findByNomAndPrenom(String nom, String prenom);
   List<Annuaire> findByNomAndPrenomAndDepartement(String nom, String prenom, String departement);
}
