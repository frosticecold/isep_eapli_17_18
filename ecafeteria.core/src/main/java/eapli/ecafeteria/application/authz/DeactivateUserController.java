/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.reasons.Reason;
import eapli.ecafeteria.domain.reasons.ReasonType;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReasonRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.util.DateTime;
import java.util.Arrays;

/**
 *
 * @author Fernando
 */
public class DeactivateUserController implements Controller {

    private final TransactionalContext tx = PersistenceContext.repositories().buildTransactionalContext();
    private final UserRepository userRepository = PersistenceContext.repositories().users(tx);
    private final ReasonRepository reasonRepository = PersistenceContext.repositories().reasons(tx);

    public Iterable<SystemUser> activeUsers() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        return this.userRepository.findAll();
    }

    public Iterable<ReasonType> getAllReasons() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        return Arrays.asList(ReasonType.values());
    }

    public SystemUser deactivateUser(final SystemUser user, final ReasonType reason, final String comment) throws DataConcurrencyException, DataIntegrityViolationException {
        if (user == null || reason == null) {
            throw new IllegalArgumentException("Error, invalid argument");
        }
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        Reason why = new Reason(user, reason, comment);
        user.deactivate(DateTime.now());
        tx.beginTransaction();
        SystemUser saved_user = userRepository.save(user);
        Reason saved_reason = reasonRepository.save(why);
        tx.commit();
        return saved_user;
    }
}
