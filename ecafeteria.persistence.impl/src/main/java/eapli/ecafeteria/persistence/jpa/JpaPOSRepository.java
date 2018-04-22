package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository {

    @Override
    public void delete(POS entity) throws DataIntegrityViolationException {
        
        entityManager().remove(entity);
    }

    @Override
    public void delete(Long entityId) throws DataIntegrityViolationException {
       
        String query = "SELECT POS.*" +
                    "FROM POS" +
                    "WHERE e.id = id";
        
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        q.setParameter("id",entityId);
        
        POS entity = (POS) q.getSingleResult();
        
        entityManager().remove(entity);
    }

    @Override
    public POS save(POS entity) throws DataConcurrencyException, DataIntegrityViolationException {
       entityManager().persist(entity);
       
       return entity;
    }

    @Override
    public Iterable<POS> findAll() {
        String query = "SELECT POS.*"
                        + "FROM POS pos";
        
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        return q.getResultList();
    }

    @Override
    public Optional<POS> findOne(Long id) {
        return matchOne("e.id=id","id",id);
    }

    @Override
    public long count() {
       return 0;
    }

  
}
