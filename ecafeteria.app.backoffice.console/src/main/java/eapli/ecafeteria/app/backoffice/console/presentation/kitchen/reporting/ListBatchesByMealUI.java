/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.app.backoffice.console.presentation.Alert.AlertUI;
import eapli.ecafeteria.application.kitchen.ListBatchesByMealController;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.MealMaterial;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.List;

public class ListBatchesByMealUI extends AlertUI {

    ListBatchesByMealController controller = new ListBatchesByMealController();

    @Override
    protected boolean doShow() {
        Calendar cal = Console.readCalendar("Introduza a data da refeição que pretende escolher: (dd-mm-aaaa)");

        List<Meal> lm = controller.getMealByDate(cal);
        int meal, j = 0;
        if (!lm.isEmpty()) {

            for (Meal m : lm) {
                System.out.println("Refeição:" + (j + 1) + "\n" + m.toString());
                j++;
            }

            do {
                meal = Console.readInteger("Escolha uma das refeições:");
            } while (meal < 0 && meal > lm.size());

            Meal m = lm.get(meal - 1);
            
            List<MealMaterial> lmm = controller.getMealMaterialFromMeal(m);
            if (!lmm.isEmpty()) {
                for (MealMaterial mm : lmm) {
                    Batch lote = controller.getBatchFromMealMaterial(mm);
                    System.out.println(lote.toString());
                }
            } else {
                System.out.println("Não existem lotes associados a essa refeição.");
            }
            

            
        } else {
               System.out.println("Nao existem refeições para essa data.");
        }

        return false;
    }

    @Override
    public String headline() {
        return super.headline() + "Show Batch Usage";
    }

}