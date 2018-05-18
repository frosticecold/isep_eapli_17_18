package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteriauser.AllergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.AllergenProfileRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Rui Almeida <1160818@isep.ipp.pt>
 */
public class InMemoryAllergenRepository extends InMemoryRepository<AllergenProfile, Long> implements AllergenProfileRepository{

    @Override
    protected Long newKeyFor(AllergenProfile entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
    @Override
    public AllergenProfile findUserAllergenProfile(CafeteriaUser user) {
        return matchOne(e -> e.user().equals(user)).get();
    }

    @Override
    public void saveAlergenProfile(AllergenProfile ap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
