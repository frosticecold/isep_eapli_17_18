/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.framework.application.Controller;

/**
 *
 * @author Oliveira
 */
public class ClosePOSController implements Controller {

   
    public void closeSession(){
        AuthorizationService.clearSession();
    }
    
    public Iterable<Integer> listDeliveredMeals(){
        return null;
    }
}
