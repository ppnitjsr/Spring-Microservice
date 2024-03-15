package com.prince.user.service.UserService.external.services;

import com.prince.rating.RatingService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotels/{hotelId}")
     Hotel getHotel(@PathVariable String hotelId);

}
