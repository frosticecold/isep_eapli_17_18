/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.RegisterComplaintController;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import javax.persistence.NoResultException;

/**
 *
 * @author Telmo
 */
public class RegisterComplaintUI extends AbstractUI{
    RegisterComplaintController controller = new RegisterComplaintController();
    CafeteriaUser u;
    Dish dish;
    
    @Override
    protected boolean doShow() {
        try {
            System.out.println("------------------------\nREGISTER COMPLAINT:\n------------------------\n");
            int option = 0;
            int option1 = 0;
            
            while(option!=1&&option!=2){
                System.out.println("Anonymous complaint?\n");
                System.out.println("1.Yes\n");
                System.out.println("2.No\n");
                option = Console.readInteger("");
            }
            
            if(option == 1){
                u = null;
            }else{
                try{
                    String mn;
                    System.out.println("Mecanographic Number:\n");
                    mn = Console.readLine("");
                    MecanographicNumber number = new MecanographicNumber(mn);
                    u = controller.getUserById(number).get();
                }catch(NoResultException e){
                    System.out.println("User Not Found!\n" + e.getMessage());
                }
            }
            
            while(option1!=1&&option1!=2){
                System.out.println("Add dish?\n");
                System.out.println("1.Yes\n");
                System.out.println("2.No\n");
                option1 = Console.readInteger("");
            }
            
            if(option1 == 1){
                try{
                    Iterable<Dish> list = controller.getDishs();
                    int it = 1;
                    int it1=1;
                    int sel = 0;
                    for (Dish dish : list ) {
                        System.out.println("---------------------");
                        System.out.println(it);
                        System.out.println(dish.name().toString());
                        it++;
                    }
                    while(sel < 1 || sel > it){
                        System.out.println("Select a Dish:\n");
                        sel = Console.readInteger("");
                    }
                    
                    for(Dish dish1 : list){
                        if(it1 == sel){
                            dish = dish1;
                            break;
                        }else{
                            it1 ++;
                        }
                    }
                }catch(NoResultException e){
                    System.out.println("No dishes on data base!\n" + e.getMessage());
                }
            }else{
                dish = null;
            }
            
            System.out.println("Description:\n");
            String d = Console.readLine("");
            
            controller.saveComplaint(d, u, dish);
            
            
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Error!\n");
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
    
}
