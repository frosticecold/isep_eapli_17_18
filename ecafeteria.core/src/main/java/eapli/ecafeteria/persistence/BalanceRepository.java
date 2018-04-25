/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public interface BalanceRepository  extends DataRepository<Balance, Long> {

     public Balance getBalanceOfUser(MecanographicNumber user);
}
