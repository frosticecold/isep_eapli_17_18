/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meal.Execution;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.ExecutionRepository;
import java.util.Calendar;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author MFerreira
 */
public class JpaExecutionRepository extends CafeteriaJpaRepositoryBase<Execution, Long> implements ExecutionRepository{

    @Override
    public Iterable<Execution> findMealExecutionByDate(Calendar cal, MealType mealType) {
        final Query q;
        
        q = entityManager().createQuery("SELECT e FROM Execution exec Meal meal "
                                        +"WHERE meal.id=exec.mealid"
                                        +"AND meal.mealtype:=mealType "
                                        +"AND meal.date:=date", this.entityClass);
        q.setParameter("date", cal, TemporalType.DATE);
        
        return q.getResultList();
    }
    
}
