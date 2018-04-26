package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MealMaterial implements AggregateRoot<String>, Serializable {
    @Id
    @GeneratedValue
    private long pk;
    @OneToOne
    private Material material;
    @OneToOne
    private Meal meal;
    @OneToOne
    private Batch batch;
    private double quantity;

    public MealMaterial(Material material, Meal meal, Batch batch, double qntd) {
        this.material = material;
        this.meal = meal;
        this.batch = batch;
        this.quantity = qntd;
    }

    protected MealMaterial() {
    }

    public Meal meal() {
        return this.meal;
    }

    @Override
    public boolean sameAs(Object other) {
        return this == other;
    }

    @Override
    public String id() {
        return material.id() + "-" + meal.id();
    }
}
