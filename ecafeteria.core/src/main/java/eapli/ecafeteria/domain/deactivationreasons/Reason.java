/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.deactivationreasons;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Reason implements ValueObject,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Cafeteria user
     */
    @OneToOne
    private SystemUser user;

    /**
     * Reason type
     */
    @Enumerated(EnumType.ORDINAL)
    private DeactivationReasonType reason_type;

    /**
     * Admin's comment
     */
    private String comment;

    //for ORM
    protected Reason() {
    }

    public Reason(final SystemUser user, final DeactivationReasonType reasonType, final String comment) {
        if (user == null || reasonType == null || comment == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }
        this.user = user;
        this.reason_type = reasonType;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public DeactivationReasonType getReasonType() {
        return reason_type;
    }
}
