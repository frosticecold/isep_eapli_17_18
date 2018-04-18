package eapli.ecafeteria.application.reporting.booking;


import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BookingReportingController implements Controller {

    private final BookingReportingRepository repo = PersistenceContext.repositories().bookingReporting();

    /**
     * reports booking
     *
     * in this case we are using a custom reporting DTO
     *
     * @return
     */
    public Iterable<BookingPerOption> reportDishesPerDishType(Date wantedDay) {

        List<BookingPerOption> l = new ArrayList<>();
        l.add(new BookingPerOption("1", new Date(), "mealDishName", "userName"));
        Iterable<BookingPerOption> it = repo.showReportByDay(wantedDay);
        
        
        return it;
    }


}
