package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.persistence.repositories.ReportingRepository;
import java.util.Date;

public interface MealReportRepository extends ReportingRepository{
    
    public Iterable<Booking> reportBookingPerDay(Date date);

    public Iterable<Booking> reportBookingPerType();

    public Iterable<Booking> reportBookingPerMeal(Meal m);
    
}
