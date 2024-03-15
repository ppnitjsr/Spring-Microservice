package com.prince.user.service.UserService.services.impl;

import com.prince.rating.RatingService.entities.Hotel;
import com.prince.user.service.UserService.entities.Rating;
import com.prince.user.service.UserService.entities.User;
import com.prince.user.service.UserService.exceptions.ResourceNotFoundException;
import com.prince.user.service.UserService.external.services.HotelService;
import com.prince.user.service.UserService.repositories.UserRepository;
import com.prince.user.service.UserService.services.UserService;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate ;

    private final UserRepository userRepository;

    @Autowired
    HotelService hotelService;

    public UserServiceImpl(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> allUsers = this.userRepository.findAll();
        for(User user : allUsers){
            ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/getById/"+user.getUserId(), ArrayList.class);
            user.setRatings(ratingsOfUser);
        }
       return  allUsers;
        //  List<Rating> ratings = Arrays.stream(userRatings).toList();
       // user.setRatings(ratingsOfUser);
    }

    @Override
    public User getUser(String userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        //fetch rating from rating service

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/getById/"+userId, Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> collect = ratings.stream().map(rating -> {
          //  ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());


        user.setRatings(collect);

        return user;
    }
}
