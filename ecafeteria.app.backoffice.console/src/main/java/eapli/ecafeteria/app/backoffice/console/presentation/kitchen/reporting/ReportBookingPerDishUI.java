package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.application.reporting.booking.BookingReportingController;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.util.Console;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author DAVID
 */
public class ReportBookingPerDishUI extends AbstractListUI<BookingPerOption> {
    
    private final BookingReportingController thisController = new BookingReportingController();
    
    public static final String BOOKING_PER_DISH_EN = "BOOKING PER DISH";
    public static final String BOOKING_PER_DISH_LH = "BOOKING PER DISH";

   @Override
    protected Iterable<BookingPerOption> elements() {
        
        String dishTypeAcronym = askForDishType();
        
        return thisController.reportDishesPerDishType(dishTypeAcronym);
    }

    @Override
    protected Visitor<BookingPerOption> elementPrinter() {
        return new BookingPerDishPrinter();
    }



    private String askForDishType() {
        int option = Console.readInteger("Choose dish type:\n   1- Vegie\n   2- Fish\n   3- Meat\n");
        
        
        switch (option) {
            case 1:
                return "vegie";
                
            case 2:
                return "fish";
        
            case 3:
                return "meat";
                
            default:
                throw new AssertionError("Invalid Type");
        }
        
        
    }

    @Override
    protected String elementName() {
        return BOOKING_PER_DISH_EN;
    }

    @Override
    protected String listHeader() {
        return BOOKING_PER_DISH_LH;
    }
    
}
