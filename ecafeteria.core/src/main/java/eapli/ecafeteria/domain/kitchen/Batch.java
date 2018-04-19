package eapli.ecafeteria.domain.kitchen;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class Batch implements Serializable {
    private static final long serialVersionUID = 4874306427335164555L;

    @Id
    private long barCode;
    @Version
    private Long version;

    /**
     * Data de validade
     */
    @Temporal(TemporalType.DATE)
    private Calendar date;

    private Material material;

    protected Batch() {
    }

    public Batch(long barCode, Calendar date, Material material) {
        this.barCode = barCode;
        this.date = date;
        this.material = material;
    }

    public long barcode() {
        return this.barCode;
    }

    public Calendar useByDate() {
        return this.date;
    }

    public Material material() {
        return material;
    }
}
