package com.prince.user.service.UserService.external.services;

import com.prince.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    @PostMapping("/ratings/")
    public Rating createRating(Rating values);

  //  public Rating updateRating();
}
