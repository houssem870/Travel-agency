package com.ditraacademy.travel_agency.core.voyage;

import com.ditraacademy.travel_agency.core.destination.Destination;
import com.ditraacademy.travel_agency.utils.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "deleted=false")
@SQLDelete(sql= "Update voyage SET deleted=true where id=?")
public class Voyage extends Audible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    private String description;
    private Date date;
    private Integer nbPlaces;
    private Double prix;

    @JsonIgnore
    private boolean deleted=false;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Destination destination;
}
