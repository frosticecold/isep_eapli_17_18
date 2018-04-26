/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.BalanceRepository;
import javax.persistence.Query;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class JPAUserBalance extends CafeteriaJpaRepositoryBase<Balance, Long> implements BalanceRepository {

    @Override
    public Balance getBalanceOfUser(MecanographicNumber user) {
        Query q = entityManager().
                createQuery("SELECT currentBalance FROM CafeteriaUser"
                        + " WHERE mecanographicNumber=:user", Balance.class);

        q.setParameter("user", user);
        return (Balance) q.getSingleResult();
    }

}
