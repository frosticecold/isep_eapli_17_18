/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.framework.domain.Designation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import eapli.ecafeteria.persistence.AlergenRepository;

/**
 *
 * @author Car
 */
public class JpaAlergensRepository extends CafeteriaJpaRepositoryBase<Alergen, Designation> implements AlergenRepository{

    @Override
    public Optional<Alergen> findByName(Designation name) {
        
	final Map<String, Object> params = new HashMap<>();
	params.put("name", name);
	return matchOne("e.name=:name", params);
	
    }

    @Override
    public List<Alergen> findAll() {
         return entityManager().createQuery("SELECT a "
                + "FROM Alergen a")
                .getResultList();

    }
 
}
