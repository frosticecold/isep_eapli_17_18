package eapli.ecafeteria.reporting.booking;

import eapli.framework.dto.DTO;
import java.util.Date;

public class BookingPerOption implements DTO{
    
    public String mealType;
    public Date mealDate;
    public String mealDishName;
    
    public String userName;

    public BookingPerOption(String mealType, Date mealDate, String mealDishName, String userName) {
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.mealDishName = mealDishName;
        this.userName = userName;
    }
    

    
}
