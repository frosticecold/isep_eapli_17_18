package eapli.ecafeteria.domain.KitchenAlert;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Embeddable;

/**
 *
 * @author DAVID
 */
@Embeddable
public class AlertLimit implements Serializable{

    private float limitValue;
    
    protected AlertLimit(){
        
    }

    /**
     * Initializes limit with value if appropriate;
     * @param limitValue value to initialize object with
     * @throws java.lang.Exception
     */
    public AlertLimit(float limitValue) throws Exception{
        configureLimitValue(limitValue);
    }
    
    public double getLimitValue() {
        return limitValue;
    }

    // throws checked exception to force validation of value
    public final void configureLimitValue(float limitValue) throws Exception {
        BigDecimal value = BigDecimal.valueOf(limitValue);
        if(value.compareTo(BigDecimal.valueOf(0))>=0 && value.compareTo(BigDecimal.valueOf(100))<=0){
            this.limitValue=limitValue;
        } else {
            throw new Exception("Invalid limit value. Limit must be between 0 and 100");
        }
   }
}
