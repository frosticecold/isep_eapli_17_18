/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.AddAllergenProfileController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.CreateAlergenProfileController;
import eapli.ecafeteria.application.cafeteriauser.RemoveAlergenProfileController;
import eapli.ecafeteria.domain.dishes.Alergen;
import java.util.Scanner;

/**
 *
 * @author utilizador
 */
public class RemoveAlergenProfileUI extends CafeteriaUserBaseUI {

    private RemoveAlergenProfileController controller = new RemoveAlergenProfileController();
    Scanner ler = new Scanner(System.in);

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    protected boolean doShow() {

        String op;
        do {
            
            
            System.out.println(" ");
            for(Alergen a : controller.getApAlergens()){
                System.out.println(a.id());
            }
       
            
            System.out.println(" ");
            System.out.println("CHOOSE ONE OF THE ALERGENS TO REMOVE IT:");
           
            String des = ler.nextLine();
            controller.remove(controller.getAlergenByDesc(des));
            System.out.println(" ");
            System.out.println("ADDED!");
            System.out.println("THIS IS YOUR CURRENT ALERGEN PROFILE ");
            System.out.println(" ");
            for(Alergen a : controller.getApAlergens()){
                System.out.println(a.id());
            }
            System.out.println(" ");
            
            System.out.println("DO YOU WANT TO REMOVE MORE ALERGENS TO THE PROFILE?");
            System.out.println("Y/N");
            op = ler.nextLine();
        } while (op.equals("Y") || controller.getAllAlergensNotInAP().isEmpty());
        controller.save();
        return true;
    }

    @Override
    public String headline() {
        return "ADD NEW ALERGEN TO THE PLAN";
    }

}
