package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.meal.Execution;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.DeliveryMealSessionRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.framework.application.Controller;
import java.util.Calendar;
import static java.util.Calendar.HOUR_OF_DAY;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;


/**
 *
 * @author Joao Magalhaes
 */
public class EndShiftController implements Controller{
    private final DeliveryMealSessionRepository repository1 = PersistenceContext.repositories().deliveryMealRepository();
    private final POSRepository repository2 = PersistenceContext.repositories().posRepository();
    private final ExecutionRepository res3 = PersistenceContext.repositories().executions();
  //  private final DeliveryRegistryRepository res4 = PersistenceContext.repositories().;

    public void presentMealsMadeNotSold() {
        int total = 0;
        int dia = 0;
        int executados = 0;
        MealType mealType=null;
        Calendar data = Calendar.getInstance();
        int hora = data.get(HOUR_OF_DAY);
        if (hora<15) {
            data.add(Calendar.DAY_OF_MONTH, -1);
            dia = Calendar.DAY_OF_MONTH-1;
            mealType=MealType.DINNER;
        }else if (hora <22) {
            mealType=MealType.LUNCH;
        }else {
            mealType=MealType.DINNER;
        }
        for (Execution it : res3.findMealExecutionByDate(data, mealType)) {
            executados = executados + it.madeMeals().madeMeals();
        }
        
       //BUUUSCAR ENTREGUES 
        //DeliveryRegistry
        
        for (DeliveryMealSession deliveryMealSession : repository1.findAll()) {
           // deliveryMealSession.
          //  total += mealCaixa;
        }
        
        System.out.println("The number of meals made but not sold on " + dia +" of " + Calendar.MONTH + " of " + Calendar.YEAR + " is : " + total + "\n");
    }
    
//    public boolean endSessions(){
//        for (POS caixa : repository2.findAll()) {
//            if(!caixa.isClosed()){
//                
//                for(DeliveryMealSession session :repository1.findAll()){
//                    if (session.loggedPOS().equals(caixa)){
//                        closeSession();
//                    }
//                }
//                caixa.changeState();
//            }
//        }
//        return true;
//    }
    
    public void closeSession(){
        AuthorizationService.clearSession();
    }
}
