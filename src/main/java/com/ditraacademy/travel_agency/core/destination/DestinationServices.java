package com.ditraacademy.travel_agency.core.destination;
import com.ditraacademy.travel_agency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationServices {

    @Autowired
    DestinationRepository destinationRepository;

        public ResponseEntity<?> creatDest(Destination destination) {
            if (destination.getTitre()==null)
                return new ResponseEntity<>(new ErrorResponseModel("destination title required"), HttpStatus.BAD_REQUEST);
            if (destination.getTitre().length()<2)
                return new ResponseEntity<>(new ErrorResponseModel("wrong destination title"),HttpStatus.BAD_REQUEST);
            if (destination.getDescription()==null)
                return new ResponseEntity<>(new ErrorResponseModel("destination description required"),HttpStatus.BAD_REQUEST);
            if (destination.getDescription().length()<4)
                return new ResponseEntity<>(new ErrorResponseModel("wrong destination description"),HttpStatus.BAD_REQUEST);

            destination=destinationRepository.save(destination);
            return new ResponseEntity<>(destination,HttpStatus.OK);
        }


        public List<Destination> getAllDest() {
            List<Destination> destList = destinationRepository.findAll();
            return destList;
        }

        public ResponseEntity<?> updateDest(int id, Destination updatedDest) {
            Optional<Destination> DestinationOptional = destinationRepository.findById(id);
            if(!DestinationOptional.isPresent())
                return new ResponseEntity<>(new ErrorResponseModel("wrong destination id "),HttpStatus.BAD_REQUEST);

            Destination databaseDest = DestinationOptional.get();

            if(updatedDest.getTitre() != null)
                if (updatedDest.getTitre().length() < 2)
                    return new ResponseEntity<>(new ErrorResponseModel("short destination title"),HttpStatus.BAD_REQUEST);
                else
                    databaseDest.setTitre(updatedDest.getTitre());

            if(updatedDest.getDescription() != null)
                if (updatedDest.getDescription().length() < 4)
                    return new ResponseEntity<>(new ErrorResponseModel("short destination description"),HttpStatus.BAD_REQUEST);
                else
                    databaseDest.setDescription(updatedDest.getDescription());

            destinationRepository.save(databaseDest);
            return new ResponseEntity<>(HttpStatus.OK);
        }


        public ResponseEntity<?> deleteDest(int id) {
            Optional<Destination> databaseDestOptional = destinationRepository.findById(id);
            if (! databaseDestOptional.isPresent()){
                ErrorResponseModel errorResponseModel=new ErrorResponseModel("destination id not found");
                return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);

            }
            destinationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        }

        public ResponseEntity<?> getDestById (int id){
            Optional<Destination> OpDest=destinationRepository.findById(id);
            if(! OpDest.isPresent()){
                ErrorResponseModel errorResponseModel= new ErrorResponseModel("wrong id");
                return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
            }
            Destination Dest=OpDest.get();
            return new ResponseEntity<>(Dest, HttpStatus.OK);
        }
}

