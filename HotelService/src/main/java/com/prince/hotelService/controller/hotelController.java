package com.prince.hotelService.controller;

import com.prince.hotelService.entites.Hotel;
import com.prince.hotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class hotelController {


    private HotelService hotelService ;

    public hotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/addHotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = this.hotelService.create(hotel);
        return new ResponseEntity<>(hotel1,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> all = this.hotelService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        Hotel hotel = this.hotelService.get(hotelId);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }
}
