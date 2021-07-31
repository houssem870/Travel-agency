package com.ditraacademy.travel_agency.core.chambre.Chambre;
import com.ditraacademy.travel_agency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travel_agency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travel_agency.core.hotel.Hotel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints ={
        @UniqueConstraint(columnNames = {"categorie_chambre_id","type_chambre_id"})
})
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private CategorieChambre categorieChambre;

    @ManyToOne
    private TypeChambre typeChambre;

    @ManyToMany(mappedBy = "chambres")
    private List<Hotel> hotels;


}
