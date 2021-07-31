package com.ditraacademy.travel_agency.core.chambre.Chambre;

import com.ditraacademy.travel_agency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travel_agency.core.chambre.typeChambre.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChambreRepository extends JpaRepository<Chambre,Integer> {
    Optional<Chambre> findByCategorieChambreAndTypeChambre (CategorieChambre categorieChambre, TypeChambre typeChambre);
}
