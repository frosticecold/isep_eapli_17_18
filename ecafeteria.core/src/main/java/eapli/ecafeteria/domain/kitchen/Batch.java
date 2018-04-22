package eapli.ecafeteria.domain.kitchen;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

@Entity
public class Batch implements Serializable {
    private static final long serialVersionUID = 4874306427335164555L;

    @Id
    @GeneratedValue
    private int pk;

    private String barCode;
    @Version
    private Long version;
    /**
     * It references if the batch has been used or not
     * <p>
     * 0 - Available
     * 1 - Used
     */
    private int status = 0;

    private double quantity;
    private double availableQuantitity;

    /**
     * Data de validade
     */
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @OneToOne
    private Material material;

    protected Batch() {
    }

    public Batch(String barCode, Calendar date, Material material, double quantity) {
        this.barCode = barCode;
        this.date = date;
        this.material = material;
        this.quantity = quantity;
        this.availableQuantitity = quantity;
    }

    public String barcode() {
        return this.barCode;
    }

    public Calendar useByDate() {
        return this.date;
    }

    public Material material() {
        return material;
    }

    public void used() {
        status = 1;
    }

    public Object pk() {
        return pk;
    }

    public boolean isAvailable() {
        return status == 0;
    }

    public double availableQuantity() {
        return availableQuantitity;
    }

    public void updatePercentageUsed(double quantity) throws Exception {
        double aux = this.availableQuantitity - quantity;

        if (aux < 0) {
            throw new Exception("Quantity used is over available quantity!");
        } else if (aux > 0) {
            this.availableQuantitity = aux;
        } else {
            this.availableQuantitity = aux;
            used();
        }
    }
}
