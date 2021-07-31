package com.ditraacademy.travel_agency.core.voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoyageController {

    @Autowired
    VoyageRepository voyageRepository;

    @Autowired
    VoyageServices voyageServices;

    @PostMapping("/voy")
    public ResponseEntity<?> createVoy (@RequestBody Voyage voyage){
         voyage= voyageRepository.save(voyage);
         return new ResponseEntity<>(voyage,HttpStatus.OK);
    }

    @GetMapping("/voyages")
    public List<Voyage> getVoy(){
        return voyageServices.getAllVoy();
    }

    @GetMapping("/voyagesQ")
    public List<Voyage> getVoyQ(){
        return voyageServices.getAllVoyQ();
    }

    @GetMapping("/voy/{id}")
    public ResponseEntity<?> getVoyById (@PathVariable int id){
        return voyageServices.getVoyById(id);
    }

    @GetMapping("/voy")
    public ResponseEntity<?> getVoyByPrix (@RequestParam double prixInf, @RequestParam double prixSup){
        return voyageServices.getVoyByPrix(prixInf,prixSup);
    }



    @PutMapping("/voy/{id}")
    public ResponseEntity<?> updateVoy(@PathVariable int id, @RequestBody Voyage updatedVoy){
        return voyageServices.updateVoy(id, updatedVoy);
    }


    @DeleteMapping("/voy/{id}")
    public ResponseEntity<?> deleteVoy(@PathVariable int id) {
        return voyageServices.deleteVoy(id);
    }

}
