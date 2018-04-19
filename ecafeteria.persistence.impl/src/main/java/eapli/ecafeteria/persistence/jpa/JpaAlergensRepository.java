/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.persistence.AlergenRepository;
import eapli.framework.domain.Designation;

/**
 *
 * @author Car
 */
public class JpaAlergensRepository extends CafeteriaJpaRepositoryBase<Alergen, Designation> implements AlergenRepository{
    
}
