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
import eapli.ecafeteria.domain.pos.DescriptiveText;
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
                controller.setUser(null);
            }else{
                try{
                    String mn;
                    System.out.println("Mecanographic Number:\n");
                    mn = Console.readLine("");
                    MecanographicNumber number = new MecanographicNumber(mn);
                    CafeteriaUser u;
                    u = controller.getUserById(number).get();
                    controller.setUser(u);
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
                    while(sel < 1 || sel > it-1){
                        System.out.println("Select a Dish:\n");
                        sel = Console.readInteger("");
                    }
                    
                    for(Dish dish1 : list){
                        if(it1 == sel){
                            controller.setDish(dish1);
                            break;
                        }else{
                            it1 ++;
                        }
                    }
                }catch(NoResultException e){
                    System.out.println("No dishes on data base!\n" + e.getMessage());
                }
            }else{
                controller.setDish(null);
            }
            DescriptiveText description = null;
            boolean flag = false;
            while(flag == false){
                System.out.println("Description:\n");
                String d = Console.readLine("");
                description = new DescriptiveText(d);
                flag = description.checkDescriptiveText();
            }
            controller.saveComplaint(description);
            
            
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
