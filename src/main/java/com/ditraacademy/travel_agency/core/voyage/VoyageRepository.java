package com.ditraacademy.travel_agency.core.voyage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage,Integer> {
    List<Voyage> findAllByPrixBetweenAndNbPlacesIsNot(double prixInf,double prixSup, Integer nbPlace);

    @Query(value = "select * from voyage where nb_places != ?1 ", nativeQuery = true)
    List<Voyage> findAllByQuery (@Param("nb") int nb);
}
