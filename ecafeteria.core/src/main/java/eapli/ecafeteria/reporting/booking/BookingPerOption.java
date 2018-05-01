package eapli.ecafeteria.reporting.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.dto.DTO;
import java.util.Calendar;

public class BookingPerOption implements DTO{
    
    public Long bookingId;
    public Calendar mealDate;
    public String bookingState;
    public String userMacGNumber;
    public String mealType;

    public BookingPerOption(Long bookingId, Calendar mealDate, String bookingState, String userMacGNumber, String mealType) {
        this.bookingId = bookingId;
        this.mealDate = mealDate;
        this.bookingState = bookingState;
        this.userMacGNumber = userMacGNumber;
        this.mealType = mealType;
    }

    public BookingPerOption() {
    }

    public static BookingPerOption fromBookingToDTO(Booking b){
        
        return new BookingPerOption(
                
                                    b.getIdBooking(),
                                    b.getMeal().getMealDate(),
                                    b.getBookingState().actualState().name(),
                                    b.getCafeteriauser().mecanographicNumber().toString(),
                                    b.getMeal().mealtype().toString()
                
        );
     
    }

    @Override
    public String toString() {
        return "BOOKING{" + "\n BOOKING ID= " + bookingId + "\n MEAL DATE= " + mealDate.getTime() + "\n STATE= " + bookingState + "\n USER NUMBER= " + userMacGNumber + "\n Meal Type= " + mealType + "\n}\n\n";
    }
    
    
    
  
    
    
    
    

    
}
