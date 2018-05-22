/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.deactivationreasons.Reason;
import eapli.ecafeteria.domain.deactivationreasons.DeactivationReasonType;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.util.DateTime;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class DeactivateUserController implements Controller {

    private final TransactionalContext tx = PersistenceContext.repositories().buildTransactionalContext();
    private final UserRepository userRepository = PersistenceContext.repositories().users(tx);

    public Iterable<SystemUser> activeUsers() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        return this.userRepository.findAll();
    }

    public Iterable<DeactivationReasonType> getAllReasons() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        return new ArrayList<>();
    }

    public SystemUser deactivateUser(final SystemUser user, final DeactivationReasonType reason, final String comment) throws DataConcurrencyException, DataIntegrityViolationException {
        if (user == null || reason == null) {
            throw new IllegalArgumentException("Error, invalid argument");
        }
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        Reason why = new Reason(user, reason, comment);
        user.deactivate(DateTime.now());
        tx.beginTransaction();
        SystemUser saved_user = userRepository.save(user);
        tx.commit();
        return saved_user;
    }
}
