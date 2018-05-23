/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.deactivationreasons.DeactivationReasonType;
import eapli.ecafeteria.persistence.DeactivationReasonTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;

/**
 *
 * @author Fernando
 */
public class DeactivateUserController implements Controller {

    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final DeactivationReasonTypeRepository dRepo = PersistenceContext.repositories().deactivationReasonTypeRepository();

    public Iterable<SystemUser> activeUsers() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        return this.userRepository.findAllActiveUsers(true);
    }

    public Iterable<DeactivationReasonType> getAllReasonsTypes() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        return dRepo.findAll();
    }

    public SystemUser deactivateUser(final SystemUser user, final DeactivationReasonType reason, final String comment) throws DataConcurrencyException, DataIntegrityViolationException {
        if (user == null || reason == null) {
            throw new IllegalArgumentException("Error, invalid argument");
        }
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        user.deactivate(DateTime.now(), reason, comment);
        SystemUser saved_user = userRepository.save(user);
        return saved_user;
    }
}
