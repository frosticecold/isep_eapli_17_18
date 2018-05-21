package eapli.ecafeteria.app.user.console.presentation.bookings;



import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.booking.ConsultDishRatingController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 *
 * @author JoaoMagalhaes
 */
public class ConsultDishRatingUI extends CafeteriaUserBaseUI {
    
    private ConsultDishRatingController theController = new ConsultDishRatingController();
    
    @Override
    protected boolean doShow() {
        int i=1,dishNumber;
        List<Dish> ld = theController.getDishes();
     
        /*Presents all the Dishes*/
        for (Dish d : ld) {
            System.out.println("Dish:" + i + "\n" + d.toString());
            i++;
            System.out.println("***************************************************+");
        }
        
        do {
            dishNumber = Console.readInteger("Insert the number of the Dish you want");
        } while (dishNumber < 0 && dishNumber > ld.size());

        Dish dish = ld.get(dishNumber - 1);
        
         /*Gets every Meal from the selected Dish*/
        List<Meal> lm = theController.getMealsByDish(dish);
        
        if (!lm.isEmpty()) {
            List<Rating> totalRatingList = new ArrayList<>();
            
            for (Meal m : lm) {
                
                /*Gets every Booking from the actual Meal*/
                List<Booking> bookingList = theController.getAllBookingsFromMealThatAreServed(m);
            
                if (!bookingList.isEmpty()) {
                    
                    /*Stores every rating from the available bookings*/
                    for (Booking b : bookingList) {
                        Rating r = theController.getRatingFromBooking(b);
                        theController.addRatingToList(r, totalRatingList);
                    }
                }
            }
            /*Calculates the average of ratings*/
            Double mediatotal = theController.calculateMediaMeal(totalRatingList);
            
            /*Apresenta informação*/
            System.out.println("\nDish Choosen:\n " + dish.toString() + "\n");
            System.out.println("Número de Ratings: " + totalRatingList.size() + "\n");
            System.out.println("Media de Ratings: " + mediatotal + "\n");
            
            System.out.println("Operação realizada com sucesso!!!");
        } else{
             System.out.println("Operação realizada com falhou!!!");
        }
        return true;
    }
    
    @Override
    public String headline() {
        return "Consult the ratings for a Dish:";
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
