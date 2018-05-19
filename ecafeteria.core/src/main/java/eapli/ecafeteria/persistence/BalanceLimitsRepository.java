/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.BalanceLimits;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author i1613
 */
public interface BalanceLimitsRepository extends DataRepository<BalanceLimits, Long>{
    
    @Override
    Optional<BalanceLimits> findOne(Long id);

    public Optional<Money> findUserBalanceLimits(MecanographicNumber number);
}
