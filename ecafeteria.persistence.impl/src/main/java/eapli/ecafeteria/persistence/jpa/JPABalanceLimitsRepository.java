/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.BalanceLimits;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.BalanceLimitsRepository;
import eapli.framework.domain.money.Money;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author Rúben Santos
 */
public class JPABalanceLimitsRepository extends CafeteriaJpaRepositoryBase<BalanceLimits, Long> implements BalanceLimitsRepository {

    @Override
    public Optional<BalanceLimits> findOne(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id=:id", params);
    }

    @Override
    public Optional<BalanceLimits> findUserBalanceLimits(MecanographicNumber number) {
        final Query q;
        q = entityManager().createQuery("SELECT e FROM BalanceLimits"
                + "WHERE e.user.number=:number", BalanceLimits.class);
        q.setParameter("number", number);
        return (Optional<BalanceLimits>) q.getSingleResult();
    }

}
