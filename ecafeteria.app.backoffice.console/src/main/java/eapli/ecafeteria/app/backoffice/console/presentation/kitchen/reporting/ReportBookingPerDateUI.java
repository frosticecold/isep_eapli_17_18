package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.application.reporting.booking.BookingReportingController;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.util.Console;
import eapli.framework.visitor.Visitor;
import java.util.Calendar;
import java.util.Date;

public class ReportBookingPerDateUI extends AbstractListUI<BookingPerOption>{
    
    private final BookingReportingController thisController = new BookingReportingController();
    
    public static final String BOOKING_PER_DATE_EN = "BOOKING PER DATE";
    public static final String BOOKING_PER_DATE_LH = "BOOKING PER DATE";

    @Override
    protected String elementName() {
        return BOOKING_PER_DATE_EN;
    }

    @Override
    protected String listHeader() {
        return BOOKING_PER_DATE_LH;
    }
    

    @Override
    protected Iterable<BookingPerOption> elements() {
        
      Calendar date = askForDate();
      
      return thisController.reportDishesPerDate(date);
    }

    @Override
    protected Visitor<BookingPerOption> elementPrinter() {
        return new BookingPerDatePrinter();
    }
    
    private Calendar askForDate(){
        
         return Console.readCalendar("Insert date (DD-MM-YYYY):");
    }
    
}
