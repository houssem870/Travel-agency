package com.ditraacademy.travel_agency.core.chambre.Chambre;
import com.ditraacademy.travel_agency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travel_agency.core.chambre.categorieChambre.CategorieChambreRepository;
import com.ditraacademy.travel_agency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travel_agency.core.chambre.typeChambre.TypeChambreRepository;
import com.ditraacademy.travel_agency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChambreServices {

    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    CategorieChambreRepository categorieChambreRepository;
    @Autowired
    TypeChambreRepository typeChambreRepository;

    public ResponseEntity<?> creatChambre (Chambre chambre) {

            Optional<CategorieChambre> catChambreOptional = categorieChambreRepository.findById(chambre.getCategorieChambre().getId());

            if (!catChambreOptional.isPresent()) {
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong categorie chambre");
                return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
            }

            Optional<TypeChambre> typeChambreOptional = typeChambreRepository.findById(chambre.getTypeChambre().getId());

            if (!typeChambreOptional.isPresent()) {
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong type chambre");
                return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
            }

            Optional<Chambre> chambreOptional=chambreRepository.findByCategorieChambreAndTypeChambre(chambre.getCategorieChambre(),chambre.getTypeChambre());

            if(chambreOptional.isPresent()){
                return new ResponseEntity<>(new ErrorResponseModel("chambre existante"),HttpStatus.BAD_REQUEST);
            }

        chambre=chambreRepository.save(chambre);
        return new ResponseEntity<>(chambre, HttpStatus.OK);

//        try{
//            chambre=chambreRepository.save(chambre);
//            return new ResponseEntity<>(chambre, HttpStatus.OK);
//        } catch (Exception exception){
//            return
//        }
    }

    public List<Chambre> getAllCham() {
        List<Chambre> chamList = chambreRepository.findAll();
        return chamList;
    }

    public ResponseEntity<?> updateCham (int id, Chambre updatedCham) {
        Optional<Chambre> ChambreOptional = chambreRepository.findById(id);
        if (!ChambreOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong chambre id "), HttpStatus.BAD_REQUEST);

        Chambre databaseCham = ChambreOptional.get();

        if (updatedCham.getCategorieChambre().getId()!=null)
            databaseCham.setCategorieChambre(updatedCham.getCategorieChambre());
        if (updatedCham.getTypeChambre().getId()!=null)
            databaseCham.setTypeChambre(updatedCham.getTypeChambre());

        chambreRepository.save(databaseCham);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCham (int id) {
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);
        if (! chambreOptional.isPresent()){
            ErrorResponseModel errorResponseModel=new ErrorResponseModel("chambre id not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        chambreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
