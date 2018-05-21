package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.AllergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author utilizador
 */
public interface AllergenProfileRepository extends DataRepository<AllergenProfile, Long> {

    /**
     * Searches an allergen profile in the database belonging to a CafeteriaUser
     *
     * @param user the cafeteria user that the profile belongs to
     *
     * @return User's Allergen Profile if it exists, else, throws
     * NoResultException
     *
     * @author Rui Almeida<1160818>
     */
    AllergenProfile findUserAllergenProfile(CafeteriaUser user);

    void saveAlergenProfile(AllergenProfile ap);
}
