/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.reasons.Reason;
import eapli.ecafeteria.persistence.ReasonRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class JpaReasonsRepository extends JpaAutoTxRepository<Reason, Long> implements ReasonRepository {

    public JpaReasonsRepository(TransactionalContext autoTx) {
        super(autoTx);
    }

    public JpaReasonsRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public Optional<Reason> findReasonBySystemUser(SystemUser user) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user_arg", user);
        return matchOne("e.user=:user_arg", params);
    }

}
