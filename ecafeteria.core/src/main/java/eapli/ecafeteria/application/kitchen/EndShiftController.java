package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.pos.ListAvailableMealsService;
import eapli.ecafeteria.domain.meal.Execution;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.persistence.DeliveryMealSessionRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.framework.application.Controller;
import java.util.Calendar;
import static java.util.Calendar.HOUR_OF_DAY;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.util.DateTime;

/**
 *
 * @author Joao Magalhaes
 */
public class EndShiftController implements Controller{
    private final DeliveryMealSessionRepository repository1 = PersistenceContext.repositories().deliveryMealRepository();
    private final ExecutionRepository res3 = PersistenceContext.repositories().executions();
    private final ListAvailableMealsService availableMealsService = new ListAvailableMealsService();
    
    private int dia;
    private Calendar data;
    private MealType mealType;
    
    public void presentMealsMadeNotSold() {
        data = DateTime.now();
        dia = data.get(Calendar.DAY_OF_MONTH);
        int executados = 0;
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
        
        //CALCULA Confeccionados 
        for (Execution it : res3.findMealExecutionByDate(data, mealType)) {
            executados = executados + it.madeMeals().madeMeals();
        }
        
        //CALCULA ENTREGUES 
        Long entregues = numberOfDeliveredMeals(data, mealType);
        
        System.out.println("\nThe number of meals made but not sold on " + dia +"/" + DateTime.currentMonth() + "/" + DateTime.currentYear() + " at " + mealType.toString() + " is : " + (executados-entregues) + "\n");
    }
    
    /**
     * Closes Sessions
     */
    public void closeSessions(){
        for (DeliveryMealSession session : repository1.findAllActiveDeliverySessions()){
            if(session.sessionDate().Day()==dia || session.sessionDate().Month()==data.get(data.MONTH)|| session.sessionDate().Year()==data.get(data.YEAR)){
                if ((session.isDinner() && mealType.equals(MealType.DINNER)) || (session.isLunch() && mealType.equals(MealType.LUNCH))) {
                    session.closeSession();
                    try {
                        repository1.save(session);
                    } catch (Exception e) {
                        System.out.println("Erro"+e.getMessage());
                    }

                }
            }
        }
    }
    
    /**
     * Calcula o numero de refeicoes entregues no total
     * 
     * @param cal
     * @param mt
     * @return 
     */
    public Long numberOfDeliveredMeals(Calendar cal, MealType mt) {
        System.out.println(cal.toString());
        Long calcDeliveredStatistics = availableMealsService.calcDeliveredTotal(cal,mt);
        
        return calcDeliveredStatistics;
    }
}
