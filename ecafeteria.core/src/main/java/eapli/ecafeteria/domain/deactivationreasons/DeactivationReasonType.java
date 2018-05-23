/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.deactivationreasons;

import eapli.ecafeteria.persistence.DeactivationReasonTypeRepository;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
@Entity
public class DeactivationReasonType implements AggregateRoot<String>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Reason type
     */
    private String reasonType;

    /**
     * For ORM
     */
    protected DeactivationReasonType() {
    }

    /**
     * Constructor for a DeactivationReasonType that receives a valid
     * reason_description
     *
     * @param reason_description
     */
    public DeactivationReasonType(final String reason_description) {
        if (reason_description == null || reason_description.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument");
        }
        reasonType = reason_description;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof DeactivationReasonType)) {
            return false;
        }
        final DeactivationReasonType other_reason = (DeactivationReasonType) other;
        if (this == other_reason) {
            return true;
        }
        return this.reasonType.equalsIgnoreCase(other_reason.reasonType);
    }

    @Override
    public String id() {
        return reasonType;
    }

    public String getReasonType() {
        return reasonType;
    }

    @Override
    public String toString() {
        return "DeactivationReasonType{" + "reasonType=" + reasonType + '}';
    }

}
