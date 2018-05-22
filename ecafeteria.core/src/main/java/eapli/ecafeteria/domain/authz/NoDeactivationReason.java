/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import eapli.ecafeteria.domain.deactivationreasons.DeactivationReasonType;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class NoDeactivationReason extends DeactivationReason {

    protected NoDeactivationReason() {
        super(new DeactivationReasonType("User is still activate"), "No comment");
    }

}
