package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author DAVID
 */
public class BookingPerDatePrinter implements Visitor<BookingPerOption> {



    @Override
    public void visit(BookingPerOption visitee) {
         System.out.println(visitee.toString()); 
    }
    
}
