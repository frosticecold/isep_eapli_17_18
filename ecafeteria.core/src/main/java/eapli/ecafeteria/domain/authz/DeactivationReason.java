/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import eapli.ecafeteria.domain.deactivationreasons.DeactivationReasonType;
import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
@Embeddable
public class DeactivationReason implements ValueObject, Serializable {

    /**
     * Reason type
     */
    private DeactivationReasonType reason_type;

    /**
     * Admin's comment
     */
    private String comment;

    //for ORM
    protected DeactivationReason() {
    }

    public DeactivationReason(final DeactivationReasonType reasonType, final String comment) {
        if (reasonType == null || comment == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }
        this.reason_type = reasonType;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public DeactivationReasonType getReasonType() {
        return reason_type;
    }

    @Override
    public String toString() {
        return "DeactivationReason{ reason_type=" + reason_type + ", comment=" + comment + '}';
    }
}
