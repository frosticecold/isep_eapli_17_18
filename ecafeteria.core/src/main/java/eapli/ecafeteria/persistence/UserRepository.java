package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * Created by nuno on 21/03/16.
 */
public interface UserRepository extends DataRepository<SystemUser, Username> {

    /**
     * Find all active or deactive users
     *
     * @param active
     * @return
     */
    public Iterable<SystemUser> findAllActiveUsers(boolean active);
}
