/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.persistence.repositories.ReportingRepository;

/**
 *
 * @author ruial
 */
public interface BookingReportingRepository extends ReportingRepository {
    
    public Iterable<Booking> listServedBookings();

    
}
