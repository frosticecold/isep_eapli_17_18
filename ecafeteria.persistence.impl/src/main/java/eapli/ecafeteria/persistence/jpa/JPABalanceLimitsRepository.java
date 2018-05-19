/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.BalanceLimits;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BalanceLimitsRepository;
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
    public BalanceLimits findUserBalanceLimits(CafeteriaUser user) {
        final Query q;
        q = entityManager().createQuery("SELECT e FROM BalanceLimits e "
                + "WHERE e.user=:user", BalanceLimits.class);
        q.setParameter("user", user);
        return (BalanceLimits) q.getSingleResult();
    }

}
