package com.prince.hotelService.services;

import com.prince.hotelService.entites.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);
}
