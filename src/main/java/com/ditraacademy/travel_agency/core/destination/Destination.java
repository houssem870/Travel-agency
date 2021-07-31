package com.ditraacademy.travel_agency.core.destination;
import com.ditraacademy.travel_agency.core.voyage.Voyage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "deleted=false")
@SQLDelete(sql= "Update destination SET deleted=true where id=?")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    private String description;

    @JsonIgnore
    private boolean deleted=false;

    @JsonIgnore
    @OneToMany(mappedBy = "destination", cascade = {CascadeType.REMOVE})
    private List<Voyage> voyages;
}
