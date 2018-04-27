/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Car
 */
public interface AlergenRepository extends DataRepository<Alergen, Designation>{
    //method to search an alergen by name in the repository 
    Optional<Alergen> findByName(Designation name);
}
