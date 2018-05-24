/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import java.util.Optional;

/**
 *
 * @author Rúben Santos<1161391@isep.ipp.pt>
 */
public class BalanceLimitsController {
    private final CafeteriaUserService service = new CafeteriaUserService();
    private CafeteriaUser user;
    public BalanceLimitsController(){
        
    }
    
    public boolean defineNewBalanceLimits(double limits){
        Optional<CafeteriaUser> ocu = service.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().username());
        this.user = ocu.get();
        if(this.user == null){
            return false;
        }
        boolean definedLimits = user.defineBalanceLimits(limits);
        if(!definedLimits) return false;
        service.save(user);
        return true;
    }
    
    
}
