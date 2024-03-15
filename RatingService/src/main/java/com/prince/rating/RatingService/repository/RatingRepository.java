package com.prince.rating.RatingService.repository;

import com.prince.rating.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingRepository extends JpaRepository<Rating,String> {
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
