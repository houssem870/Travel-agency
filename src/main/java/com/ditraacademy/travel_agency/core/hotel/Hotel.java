package com.ditraacademy.travel_agency.core.hotel;

import com.ditraacademy.travel_agency.core.chambre.Chambre.Chambre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String description;
    private int etoiles;
    private String adresse;
    private String telephone;

    @ManyToMany
    @JoinTable(name = "hotel_chambre",
        joinColumns = {@JoinColumn (name = "hotel_id")},
        inverseJoinColumns = {@JoinColumn (name = "chambre_id")})
    private List<Chambre> chambres;
}
