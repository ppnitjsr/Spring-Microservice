package com.prince.rating.RatingService.controller;

import com.prince.rating.RatingService.entities.Rating;
import com.prince.rating.RatingService.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1 = this.ratingService.create(rating);
        return new ResponseEntity<>(rating1, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Rating>> getAllRating(){
        List<Rating> allRating = this.ratingService.getAllRating();
        return new ResponseEntity<>(allRating,HttpStatus.OK);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        List<Rating> ratingByUserId = this.ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(ratingByUserId,HttpStatus.OK);
    }

    @GetMapping("/getByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        List<Rating> ratingByHotelId = this.ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(ratingByHotelId,HttpStatus.OK);
    }
}
