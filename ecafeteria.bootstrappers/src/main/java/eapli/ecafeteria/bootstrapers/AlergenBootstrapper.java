/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.dishes.AlergenController;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author Car
 */
public class AlergenBootstrapper implements Action {

    @Override
    public boolean execute() {
        register(TestDataConstants.ALLERGEN_CRUSTACEOS);
        register(TestDataConstants.ALLERGEN_SOJA);
        register(TestDataConstants.ALLERGEN_OVOS);
        register(TestDataConstants.ALLERGEN_PEIXES);
        register(TestDataConstants.ALLERGEN_AMENDOINS);
        register(TestDataConstants.ALLERGEN_GLUTEN);
        register(TestDataConstants.ALLERGEN_LEITE);
        register(TestDataConstants.ALLERGEN_FRUTOS_SECOS);
        register(TestDataConstants.ALLERGEN_AIPO);
        register(TestDataConstants.ALLERGEN_MOSTARDA);
        register(TestDataConstants.ALLERGEN_SESAMO);
        register(TestDataConstants.ALLERGEN_ENXOFRE);
        register(TestDataConstants.ALLERGEN_TREMOCO);
        register(TestDataConstants.ALLERGEN_MOLUSCO);
        
        return true;
        
    }
    
    /*
    / Method to register alergens in the h2 repository
    */
    private void register(String name){
        final AlergenController controller = new AlergenController();
        try {
            controller.newAlergen(name);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
