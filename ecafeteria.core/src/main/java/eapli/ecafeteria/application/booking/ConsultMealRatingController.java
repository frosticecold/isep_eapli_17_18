/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author pedro
 */
public class ConsultMealRatingController implements Controller {

    private final MealRepository mealRepo = PersistenceContext.repositories().meals();
    
    private final BookingRepository bookingRepo = PersistenceContext.repositories().booking();
    
    private final RatingRepository ratingRepo = PersistenceContext.repositories().rating();

    public Meal getMealByDate(Calendar cal) {

        return mealRepo.getMealByDate(cal);

    }
    
    public List<Booking> getAllBookingsFromMealThatAreServed(Meal m){
        
        return bookingRepo.getAllBookingsFromMealThatAreServed(m);
    }
    
    public Rating getRatingFromBooking(Booking b){
        
        return ratingRepo.getRatingFromBooking(b);
    }
    
    public void addRatingToList(Rating r,List<Rating>ratings){
        ratings.add(r);
    }
    
    public double calculateMedia(List<Rating> ratings){
        double sum=0;
        for(Rating r:ratings){
            sum+=r.getRating();
        }
        return sum/ratings.size();
    }
    
    public String showComments(Rating r){
        return r.getComment();
    }
    
    public void replyComment(String response,Rating r){
        r.setReply(response);
    }
    
    public void saveRating(Rating r) throws DataConcurrencyException, DataIntegrityViolationException{
        ratingRepo.save(r);
    }

}
