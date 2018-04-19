package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.application.reporting.booking.BookingReportingController;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import java.util.Date;

public class ReportBookingUI extends AbstractListUI<BookingPerOption>{
    
    private final BookingReportingController thisController = new BookingReportingController();

    @Override
    protected String elementName() {
        return "BOOK PER DISH TYPE";
    }

    @Override
    protected String listHeader() {
        return "BOOK PER DISH TYPE";
    }
    
//    private final DishReportingController theController = new DishReportingController();
//
//    protected Controller controller() {
//        return this.theController;
//    }
//
//    @Override
//    protected Iterable<DishesPerDishType> elements() {
//        return this.theController.reportDishesPerDishType();
//    }
//
//    @Override
//    protected Visitor<DishesPerDishType> elementPrinter() {
//        return new DishesPerDishTypePrinter();
//    }
//
//    @Override
//    protected String elementName() {
//        return "Dishes per dish type";
//    }
//
//    @Override
//    protected String listHeader() {
//        return "DISHES PER DISH TYPE";
//    }

    @Override
    protected Iterable<BookingPerOption> elements() {
        return thisController.reportDishesPerDishType(new Date());
    }

    @Override
    protected Visitor<BookingPerOption> elementPrinter() {
        return new BookingPerDatePrinter();
    }
    
}
