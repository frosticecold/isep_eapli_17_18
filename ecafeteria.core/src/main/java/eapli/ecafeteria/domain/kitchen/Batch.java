package eapli.ecafeteria.domain.kitchen;

import java.io.*;
import java.util.*;
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
     * Data de validade
     */
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @OneToOne
    private Material material;

    protected Batch() {
    }

    public Batch(String barCode, Calendar date, Material material) {
        this.barCode = barCode;
        this.date = date;
        this.material = material;
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
}
