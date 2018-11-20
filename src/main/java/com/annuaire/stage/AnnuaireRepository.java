package com.annuaire.stage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.annuaire.stage.Annuaire;

import java.util.List;

@Repository
public interface AnnuaireRepository extends JpaRepository<Annuaire, Long> {
   
}
