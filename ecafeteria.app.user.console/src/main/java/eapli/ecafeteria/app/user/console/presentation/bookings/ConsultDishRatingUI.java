package eapli.ecafeteria.app.user.console.presentation.bookings;



import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.booking.ConsultDishRatingController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.util.Console;
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
        int j=0,dishNumber;
        List<Dish> ld = theController.getDishes();
     
        for (Dish d : ld) {
            System.out.println("Dish:" + (j + 1) + "\n" + d.toString());
            j++;
            System.out.println("***************************************************+");
        }
        
        do {
            dishNumber = Console.readInteger("Insert the number of the Dish you want");
        } while (dishNumber < 0 && dishNumber > ld.size());

        Dish dish = ld.get(dishNumber - 1);
        
        List<Meal> lm = theController.getMealByDish(dish);
        
        
        if (!lm.isEmpty()) {
            for (Meal m : lm) {
                
            List<Booking> bookingList = theController.getAllBookingsFromMealThatAreServed(m);
                
            }
        } else{
             System.out.println("Não existem meals com o nome do prato introduzido");
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
