package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.KitchenAlert.AlertLimit;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;

/**
 *
 * @author DAVID
 */
public class JPAAlertRepositoryLimits extends CafeteriaJpaRepositoryBase<AlertLimit, Long> implements AlertRepositoryLimits {

    @Override
    public float[] getLimits() { //falta o man implementar na base de dados
        
        //acho que devemos mudar o tipo de float[] para uma List, pq a query só retorna para lista
        
//        Query q = entityManager().
//                createQuery("SELECT limit FROM Limit limit ", Float.class);
//
//        return q.getResultList();
        
        float yellowLimit = entityManager().createQuery("SELECT limitValue "
                + "FROM YellowAlert")
                .getFirstResult();
        float redLimit = entityManager().createQuery("SELECT limitValue "
                + "FROM RedAlert")
                .getFirstResult();
        float limits[]={yellowLimit,redLimit};
        return limits;
    }
    
}
