/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.AlergenController;
import eapli.ecafeteria.application.dishes.ChangeDishController;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 *
 * @author Car
 */
public class AddAlergenUI extends AbstractUI {

    private final ChangeDishController theController = new ChangeDishController();
    private final AlergenController theC = new AlergenController();
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<Dish> allDishes = this.theController.allDishes();
        if (!allDishes.iterator().hasNext()) {
            System.out.println("There are no registered Dishes");
        }
        else {
            final SelectWidget<Dish> selector = new SelectWidget<>("Dishes:", allDishes, new DishPrinter());
            selector.show();
            final Dish selectedDish = selector.selectedElement();
            final Iterable<Alergen> allAlergens = this.theC.allAlergens();
            if (!allAlergens.iterator().hasNext()) {
                System.out.println("There are no registered Alergens");
            }
            else {
            final SelectWidget<Alergen> selectorA = new SelectWidget<>("Alergens:", allAlergens, new AlergenPrinter());
            selector.show();
            final Alergen selectedAlegen = selectorA.selectedElement();
            selectedDish.addAlergen(selectedAlegen);
            }
        }
        return true;
    }

    @Override
    public String headline() {
         return "Add Alergen";
    }

}
