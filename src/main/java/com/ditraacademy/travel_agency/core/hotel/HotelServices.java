package com.ditraacademy.travel_agency.core.hotel;
import com.ditraacademy.travel_agency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServices {
    @Autowired
    HotelRepository hotelRepository;

    public ResponseEntity<?> createHotel(Hotel hotel) {


        if (hotel.getNom() == null)
            return new ResponseEntity<>(new ErrorResponseModel("hotel name required"), HttpStatus.BAD_REQUEST);

        if (hotel.getNom().length() < 3)
            return new ResponseEntity<>(new ErrorResponseModel("hotel name required"), HttpStatus.BAD_REQUEST);

        if (hotel.getDescription() == null)
            return new ResponseEntity<>(new ErrorResponseModel("hotel description required"), HttpStatus.BAD_REQUEST);

        if (hotel.getDescription().length() < 10)
            return new ResponseEntity<>(new ErrorResponseModel("hotel description required"), HttpStatus.BAD_REQUEST);

        if (hotel.getEtoiles() == 0)
            return new ResponseEntity<>(new ErrorResponseModel("star number required"), HttpStatus.BAD_REQUEST);

        if (hotel.getEtoiles() <= 0)
            return new ResponseEntity<>(new ErrorResponseModel("star number required"), HttpStatus.BAD_REQUEST);

        if (hotel.getAdresse().length() < 20)
            return new ResponseEntity<>(new ErrorResponseModel("hotel name required"), HttpStatus.BAD_REQUEST);

        if (hotel.getAdresse() == null)
            return new ResponseEntity<>(new ErrorResponseModel("hotel description required"), HttpStatus.BAD_REQUEST);

        if (hotel.getTelephone() == null)
            return new ResponseEntity<>(new ErrorResponseModel("phone number required"), HttpStatus.BAD_REQUEST);

        hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }


    public List<Hotel> getHotels() {
        List<Hotel> hotellist = hotelRepository.findAll();
        return hotellist;
    }


    public ResponseEntity<?> updateHotel ( Integer id , Hotel newHotel) {


        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (!hotelOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong hotel id "), HttpStatus.BAD_REQUEST);
        Hotel databasehotel = hotelOptional.get();

        if (newHotel.getNom() != null)
            if (newHotel.getNom().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModel("hotel name required "), HttpStatus.BAD_REQUEST);
            else
                databasehotel.setNom(newHotel.getNom());


        if (newHotel.getDescription().length() < 20)
            return new ResponseEntity<>(new ErrorResponseModel("hotel description false"), HttpStatus.BAD_REQUEST);
        else
            databasehotel.setDescription(newHotel.getDescription());

        if (newHotel.getEtoiles() != 0)
            if (newHotel.getEtoiles() <= 0)
                return new ResponseEntity<>(new ErrorResponseModel("star number required"), HttpStatus.BAD_REQUEST);
            else
                databasehotel.setEtoiles(newHotel.getEtoiles());

        if (newHotel.getAdresse() != null)
            if (newHotel.getAdresse().length() < 20)
                return new ResponseEntity<>(new ErrorResponseModel("False adress "), HttpStatus.BAD_REQUEST);
            else
                databasehotel.setAdresse(newHotel.getAdresse());

        if (newHotel.getTelephone() != null)
                return new ResponseEntity<>(new ErrorResponseModel("phone number required"), HttpStatus.BAD_REQUEST);
            else
                databasehotel.setTelephone(newHotel.getTelephone());

        hotelRepository.save(databasehotel);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> removeHotel (Integer id ){
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (!hotelOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong hotel id "), HttpStatus.BAD_REQUEST);


        hotelRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getHotelById (Integer id ){
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()){
            Hotel hotel = hotelOptional.get();
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        }
        else {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong hotel id");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
    }

}