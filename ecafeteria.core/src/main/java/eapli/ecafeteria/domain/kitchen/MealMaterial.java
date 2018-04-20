package eapli.ecafeteria.domain.kitchen;

import javax.persistence.*;

@Entity
public class MealMaterial {
    @Id
    @GeneratedValue
    private long pk;

    private long materialCode;
    private long mealCode;
    @OneToOne
    private Batch batch;

    public MealMaterial(long materialCode, long mealCode, Batch batch) {
        this.materialCode = materialCode;
        this.mealCode = mealCode;
        this.batch = batch;
    }

    public MealMaterial() {
    }

    public long meal() {
        return this.mealCode;
    }
}
