package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.MealMaterial;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.MealMaterialRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaMealMaterialRepository extends CafeteriaJpaRepositoryBase<MealMaterial, Long> implements MealMaterialRepository {

    @Override
    public List<Meal> getMealsByBatchID(Batch id) {
        final Query q;

        q = entityManager().createQuery("SELECT e.meal FROM MealMaterial e "
                + "WHERE e.batch=:id "
        );
        q.setParameter("id", id);
        return q.getResultList();

    }

    @Override
    public List<MealMaterial> getMealMaterialFromMeal(Meal m) {
        
        final Query q;

        q = entityManager().createQuery("SELECT e FROM MealMaterial e WHERE e.meal=:m ");
        q.setParameter("m", m);
        
        return q.getResultList();
    }

    @Override
    public Batch getBatchFromMealMaterial(MealMaterial mm) {
        TypedQuery<Batch> q;

        q = entityManager().createQuery("SELECT e.batch FROM MealMaterial e WHERE e=:mm ",Batch.class);
        q.setParameter("mm", mm);
        
        return q.getSingleResult();
    }

}
