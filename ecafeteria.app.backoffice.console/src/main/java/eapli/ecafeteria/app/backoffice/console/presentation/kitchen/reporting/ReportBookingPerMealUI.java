package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.application.reporting.booking.BookingReportingController;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.util.Console;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author DAVID
 */
public class ReportBookingPerMealUI extends AbstractListUI<BookingPerOption>{
    
    private final BookingReportingController thisController = new BookingReportingController();
    
    public static final String BOOKING_PER_MEAL_EN = "BOOKING PER DISH";
    public static final String BOOKING_PER_MEAL_LH = "BOOKING PER DISH";

    @Override
    protected Iterable<BookingPerOption> elements() {
        MealType mealType = askForMeal();
        
        return thisController.reportDishesPerMeal(mealType);
    }

    @Override
    protected Visitor<BookingPerOption> elementPrinter() {
        
        return new BookingPerMealPrinter();
    }

    @Override
    protected String elementName() {
        return BOOKING_PER_MEAL_EN;
    }

    @Override
    protected String listHeader() {
        return BOOKING_PER_MEAL_LH;
    }

    private MealType askForMeal() {
        
        int option = Console.readInteger("Choose a Meal Type:\n\n   1- LUNCH\n   2- DINNER\n\n");
        
        
        return ( (option == 1) ? MealType.LUNCH : MealType.DINNER );
    }
    
    
}
