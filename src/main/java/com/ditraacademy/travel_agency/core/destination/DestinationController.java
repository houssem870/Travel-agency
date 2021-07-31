package com.ditraacademy.travel_agency.core.destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DestinationController {

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    DestinationServices destinationServices;

    @PostMapping("/dest")
    public ResponseEntity<?> createDest (@RequestBody Destination destination){
        return destinationServices.creatDest(destination);
    }

     @GetMapping("/destinations")
     public List<Destination> getDest(){
         return destinationServices.getAllDest();
    }

    @GetMapping("/dest/{id}")
    public ResponseEntity<?> getDestById (@PathVariable int id){
        return destinationServices.getDestById(id);
    }

    @PutMapping("/dest/{id}")
    public ResponseEntity<?> updateDest(@PathVariable int id, @RequestBody Destination updatedDest){
        return destinationServices.updateDest(id, updatedDest);
    }


    @DeleteMapping("/dest/{id}")
    public ResponseEntity<?> deleteDest(@PathVariable int id) {
        return destinationServices.deleteDest(id);
    }


}

