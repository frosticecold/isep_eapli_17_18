package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.application.reporting.booking.BookingReportingController;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.presentation.console.AbstractListUI;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Visitor<BookingPerOption> elementPrinter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
