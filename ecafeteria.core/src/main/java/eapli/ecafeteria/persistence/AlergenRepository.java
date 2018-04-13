/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.Alergen;
import java.util.List;

/**
 *
 * @author Car
 */
public interface AlergenRepository {
    public Alergen add(Alergen entity);
    
    public List<Alergen> findAll();
    
    
}
