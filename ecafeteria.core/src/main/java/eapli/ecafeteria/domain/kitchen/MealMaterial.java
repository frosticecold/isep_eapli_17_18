package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meal.Meal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MealMaterial {
    @Id
    @GeneratedValue
    private long pk;
    @OneToOne
    private Material materialCode;
    @OneToOne
    private Meal meal;
    @OneToOne
    private Batch batch;
    private double quantity;

    public MealMaterial(Material material, Meal meal, Batch batch, double qntd) {
        this.materialCode = material;
        this.meal = meal;
        this.batch = batch;
        this.quantity = qntd;
    }

    public MealMaterial() {
    }

    public Meal meal() {
        return this.meal;
    }
}
