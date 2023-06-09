package com.microservice.UserService.services.impl;

import com.microservice.UserService.entities.Hotel;
import com.microservice.UserService.entities.Rating;
import com.microservice.UserService.entities.User;
import com.microservice.UserService.exceptions.ResourceNotFoundException;
import com.microservice.UserService.externals.HotelService;
import com.microservice.UserService.externals.RatingService;
import com.microservice.UserService.repositories.UserRepository;
import com.microservice.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUsers = userRepository.findAll();
        List<User> allUsersWithRating = allUsers.stream().map(user -> {
            //Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            List<Rating> ratings = ratingService.getRatingsByUser(user.getUserId());
            List<Rating> ratingsWithHotel = ratings.stream().map(rating -> {
                //Hotel hotel = restTemplate.getForObject("http://HOTEl-SERVICE/hotels/" + rating.getHotelId(), Hotel.class); // RestTemplate Approach
                Hotel hotel = hotelService.getHotel(rating.getHotelId()); // FeignClient Approach
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingsWithHotel);
            return user;
        }).collect(Collectors.toList());
        return allUsersWithRating;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server: " + userId));

        // Fetch ratings of userId from RATING-SERVICE
        // http://localhost:8083/ratings/users/1837a1b4-6830-47ae-9369-1b32c33e3c22

        //Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        List<Rating> ratings = ratingService.getRatingsByUser(userId);
        List<Rating> ratingsWithHotel = ratings.stream().map(rating -> {
            //Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class); // RestTemplate Approach
            Hotel hotel = hotelService.getHotel(rating.getHotelId()); // FeignClient Approach
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        //logger.info("{} ", ratings);
        user.setRatings(ratingsWithHotel);
        return user;
    }

    public boolean deleteUser(String userId){
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server: "+userId));
        userRepository.deleteById(userId);
        return true;
    }

    public User updateUser(User user, String userId){
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server: "+userId));
        user1.setName(user.getName() != null ? user.getName() : user1.getName());
        return userRepository.save(user1);
    }
}
