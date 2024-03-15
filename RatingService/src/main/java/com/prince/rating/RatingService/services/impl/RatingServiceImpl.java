package com.prince.rating.RatingService.services.impl;

import com.prince.rating.RatingService.Exception.ResourceNotFoundException;
import com.prince.rating.RatingService.entities.Rating;
import com.prince.rating.RatingService.repository.RatingRepository;
import com.prince.rating.RatingService.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating create(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
         return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }
}
