package com.ditraacademy.travel_agency.core.chambre.Chambre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ChambreController {
    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    ChambreServices chambreServices;

    @PostMapping("/cham")
    public ResponseEntity<?> creatChambre (@RequestBody Chambre chambre){
        return chambreServices.creatChambre(chambre);
    }

    @GetMapping("/chambres")
    public List<Chambre> getCham(){
        return chambreServices.getAllCham();
    }

    @PutMapping("/cham/{id}")
    public ResponseEntity<?> updateDest(@PathVariable int id, @RequestBody Chambre updatedCham){
        return chambreServices.updateCham(id, updatedCham);
    }


    @DeleteMapping("/cham/{id}")
    public ResponseEntity<?> deleteCham(@PathVariable int id) {
        return chambreServices.deleteCham(id);
    }


}
