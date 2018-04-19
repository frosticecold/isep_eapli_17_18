package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.application.reporting.booking.BookingReportingController;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import java.sql.Date;

public class ReportBookingPerDateUI extends AbstractListUI<BookingPerOption>{
    
    private final BookingReportingController thisController = new BookingReportingController();

    @Override
    protected String elementName() {
        return "BOOK PER DISH TYPE";
    }

    @Override
    protected String listHeader() {
        return "BOOK PER DISH TYPE";
    }
    

    @Override
    protected Iterable<BookingPerOption> elements() {
        return thisController.reportDishesPerDishType();
    }

    @Override
    protected Visitor<BookingPerOption> elementPrinter() {
        return new BookingPerDatePrinter();
    }
    
}
