package com.ditraacademy.travel_agency.core.voyage;
import com.ditraacademy.travel_agency.core.destination.Destination;
import com.ditraacademy.travel_agency.core.destination.DestinationRepository;
import com.ditraacademy.travel_agency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VoyageServices {
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<?> creatVoy(Voyage voyage) {
        if(voyage.getDestination().getId()!= null){
            Optional<Destination> destinationOptional = destinationRepository.findById((voyage.getDestination().getId()));

            if(!destinationOptional.isPresent()){
                ErrorResponseModel errorResponseModel=new ErrorResponseModel("destination not found");
                return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
            }
            // l'instruction la plus importante !!
            voyage.setDestination(destinationOptional.get());

            if (voyage.getTitre()==null)
                return new ResponseEntity<>(new ErrorResponseModel("voyage title required"), HttpStatus.BAD_REQUEST);
            if (voyage.getTitre().length()<3)
                return new ResponseEntity<>(new ErrorResponseModel("wrong voyage title"),HttpStatus.BAD_REQUEST);
            if (voyage.getDescription()==null)
                return new ResponseEntity<>(new ErrorResponseModel("voyage description required"), HttpStatus.BAD_REQUEST);
            if (voyage.getDescription().length()<3)
                return new ResponseEntity<>(new ErrorResponseModel("wrong voyage description"),HttpStatus.BAD_REQUEST);
            if (voyage.getNbPlaces()==null)
                return new ResponseEntity<>(new ErrorResponseModel("place number required"),HttpStatus.BAD_REQUEST);
            if (voyage.getNbPlaces()<0)
                return new ResponseEntity<>(new ErrorResponseModel("wrong voyage place number"),HttpStatus.BAD_REQUEST);
            if (voyage.getPrix()==null)
                return new ResponseEntity<>(new ErrorResponseModel("price required"),HttpStatus.BAD_REQUEST);
            if (voyage.getPrix()<0)
                return new ResponseEntity<>(new ErrorResponseModel("wrong voyage price"),HttpStatus.BAD_REQUEST);
        }

        voyage=voyageRepository.save(voyage);
        return new ResponseEntity<>(voyage,HttpStatus.OK);
    }


    public List<Voyage> getAllVoy() {
        List<Voyage> voyList = voyageRepository.findAll();
        return voyList;
    }

    public List<Voyage> getAllVoyQ() {
        List<Voyage> voyList = voyageRepository.findAllByQuery(0);
        return voyList;
    }

    public ResponseEntity<?> updateVoy(int id, Voyage updatedVoy) {
        Optional<Voyage> VoyageOptional = voyageRepository.findById(id);
        if(!VoyageOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong voyage id "),HttpStatus.BAD_REQUEST);

        Voyage databaseVoy = VoyageOptional.get();

        if(updatedVoy.getTitre() != null)
            if (updatedVoy.getTitre().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModel("short voyage title"),HttpStatus.BAD_REQUEST);
            else
                databaseVoy.setTitre(updatedVoy.getTitre());

        if(updatedVoy.getDescription() != null)
            if (updatedVoy.getDescription().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModel("short voyage description"),HttpStatus.BAD_REQUEST);
            else
                databaseVoy.setDescription(updatedVoy.getDescription());

        if(updatedVoy.getNbPlaces() != null)
            if (updatedVoy.getNbPlaces() < 0)
                return new ResponseEntity<>(new ErrorResponseModel("wrong voyage place number"),HttpStatus.BAD_REQUEST);
            else
                databaseVoy.setNbPlaces(updatedVoy.getNbPlaces());

        if(updatedVoy.getPrix() != null)
            if (updatedVoy.getPrix() < 0)
                return new ResponseEntity<>(new ErrorResponseModel("wrong voyage price"),HttpStatus.BAD_REQUEST);
            else
                databaseVoy.setPrix(updatedVoy.getPrix());

        voyageRepository.save(databaseVoy);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<?> deleteVoy(int id) {
        Optional<Voyage> databaseVoyOptional = voyageRepository.findById(id);
        if (! databaseVoyOptional.isPresent()){
            ErrorResponseModel errorResponseModel=new ErrorResponseModel("voyage id not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);

        }
        voyageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> getVoyById (int id){
        Optional<Voyage> OpVoy=voyageRepository.findById(id);
        if(! OpVoy.isPresent()){
            ErrorResponseModel errorResponseModel= new ErrorResponseModel("wrong id");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        Voyage Voy=OpVoy.get();
        return new ResponseEntity<>(Voy, HttpStatus.OK);
    }

    public ResponseEntity<?> getVoyByPrix (double prixInf,double prixSup){
        List<Voyage> voy;
        voy=voyageRepository.findAllByPrixBetweenAndNbPlacesIsNot(prixInf,prixSup,0);

        return new ResponseEntity<>(voy, HttpStatus.OK);
    }
}

