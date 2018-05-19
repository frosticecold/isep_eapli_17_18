/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.reasons;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Embedded;
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
@Entity
public class Reason implements AggregateRoot<SystemUser>, Serializable {

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
    private ReasonType reason_type;

    /**
     * Admin's comment
     */
    private String comment;

    //for ORM
    protected Reason() {
    }

    public Reason(final SystemUser user, final ReasonType reasonType, final String comment) {
        this.user = user;
        this.reason_type = reasonType;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Reason)) {
            return false;
        }
        final Reason other_reason = (Reason) other;
        if (this == other_reason) {
            return true;
        }
        return user.equals(other_reason.user) && reason_type == other_reason.reason_type && comment.equals(other_reason.comment);
    }

    @Override
    public SystemUser id() {
        return user;
    }

}
