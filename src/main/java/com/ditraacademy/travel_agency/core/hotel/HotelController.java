package com.ditraacademy.travel_agency.core.hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelServices hotelServices ;

    @PostMapping( "/hotel")
    public ResponseEntity<?> createHotel (@RequestBody Hotel hotel) {

        return hotelServices.createHotel(hotel);
    }

    @GetMapping("/hotels")
    public List<Hotel> gethotels(){
        List<Hotel> Hotellist =hotelServices.getHotels();
        return Hotellist ;
    }
    @PutMapping("/hotel/{id}")
    public ResponseEntity <?> updateHotel  (@PathVariable Integer id , @RequestBody Hotel hotel ) {
        return  hotelServices.updateHotel (id,hotel);

    }


    @DeleteMapping("hotel/{id}")
    public ResponseEntity deleteHotel(@PathVariable Integer id ){
        return hotelServices.removeHotel(id);
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<?> UserGet(@PathVariable int id){
        return hotelServices.getHotelById(id);
    }


}