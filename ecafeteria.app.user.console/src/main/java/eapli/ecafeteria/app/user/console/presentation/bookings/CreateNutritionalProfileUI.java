/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.CreateNutritionalProfileController;
import java.util.Scanner;

/**
 *
 * @author Rafael Teixeira
 */
public class CreateNutritionalProfileUI extends CafeteriaUserBaseUI {

    private CreateNutritionalProfileController controller = new CreateNutritionalProfileController();
    Scanner ler = new Scanner(System.in);

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    protected boolean doShow() {

        String op;
        do {
            
            System.out.println("Whats the maximum calories per Dish ?");
            int max = ler.nextInt();
            controller.maxCalDish(max);
            System.out.println(" ");
            System.out.println("Changes Saved!");
            System.out.println("This is your current maximum of calories per Dish : " + controller.getmaxCalD());
       
            System.out.println("Whats the maximum calories per Week ?");
            int maxW = ler.nextInt();
            controller.maxCalWeek(maxW);
            System.out.println(" ");
            System.out.println("Changes Saved!");
            System.out.println("This is your current maximum of calories per Week : " + controller.getmaxCalW());
            
            System.out.println("Whats the maximum salt per Dish ?");
            int maxS = ler.nextInt();
            controller.maxSaltDish(maxS);
            System.out.println(" ");
            System.out.println("Changes Saved!");
            System.out.println("This is your current maximum of salt per Dish : " + controller.getmaxSaltD());
            
            System.out.println("Whats the maximum Salt per Week ?");
            int maxSW = ler.nextInt();
            controller.maxCalDish(maxSW);
            System.out.println(" ");
            System.out.println("Changes Saved!");
            System.out.println("This is your current maximum of salt per Week : " + controller.getmaxSaltW());
           

            System.out.println(" ");
            
            System.out.println("Do you Want to change any of the values that you just insert ?");
            System.out.println("'Y' for YES and 'N' for NO");
            op = ler.nextLine();
        } while (op.equals("Y"));
        controller.save();
        return true;
    }

    @Override
    public String headline() {
        return "Create New Nutritional Profile";
    }
    
}
