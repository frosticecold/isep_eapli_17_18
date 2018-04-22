/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.ecafeteria.application.kitchen.SearchBatchUsageController;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.visitor.Visitor;
import java.util.Scanner;

/**
 *
 * @author utilizador
 */
public class SearchBatchUsageUI extends AbstractUI {

    SearchBatchUsageController controller = new SearchBatchUsageController();

    @Override
    protected boolean doShow() {
        System.out.println("Pick one of the batches");
        for (Batch e : controller.getAllBatches()) {
            System.out.println(e.info());
        }
        Scanner ler = new Scanner(System.in);
        int op = ler.nextInt();
        Batch e = controller.getBatchById(op);
        for (Meal c : controller.getMealsFromBatch(e)) {
            System.out.println(c.toString());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Show Batch Usage";
    }

}
