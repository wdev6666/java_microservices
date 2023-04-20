package com.microservice.RatingService;

import com.microservice.RatingService.entities.Rating;
import com.microservice.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating){
        Rating rating1 = ratingService.addRating(rating);
        return ResponseEntity.ok(rating1);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        List<Rating> ratings = ratingService.getRatings();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable int ratingId){
        Rating rating = ratingService.getRating(ratingId);
        return ResponseEntity.ok(rating);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable int ratingId, @RequestBody Rating rating){
        Rating rating1 = ratingService.updateRating(ratingId, rating);
        return ResponseEntity.ok(rating1);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<String> deleteRating(@PathVariable int ratingId){
        if(ratingService.deleteRating(ratingId)){
            return ResponseEntity.ok("Success");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed");
        }
    }
}
