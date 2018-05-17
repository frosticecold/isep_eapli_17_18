/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.List;
import java.util.Optional;
import eapli.ecafeteria.persistence.AlergenRepository;

/**
 *
 * @author Car
 */
public class InMemoryAlergenRepository extends InMemoryRepository<Alergen, Designation> implements AlergenRepository {

    @Override
    protected Designation newKeyFor(Alergen entity) {
        return entity.id();
    }

    @Override
    public Optional<Alergen> findByName(Designation name) {
        return matchOne(e -> e.name().equals(name));
    }

    @Override
    public List<Alergen> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
