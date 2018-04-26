package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository {

 
    @Override
    public Iterable<POS> findAll() {
        String query = "SELECT POS.*"
                        + "FROM POS pos";
        
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        return q.getResultList();
    }

    @Override
    public Optional<POS> findOne(Long id) {
        return matchOne("e.IDPOS=id","id",id);
    }

    @Override
    public long count() {
       return 0;
    }

  
}
