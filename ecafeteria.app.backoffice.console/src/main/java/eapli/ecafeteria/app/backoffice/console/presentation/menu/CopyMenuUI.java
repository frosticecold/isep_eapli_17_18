/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.menus.CopyMenuController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Oliveira
 */
public class CopyMenuUI extends AbstractUI {

    private CopyMenuController controller = new CopyMenuController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        System.out.println("TO BE IMPLEMENTED");
        return true;
    }

    @Override
    public String headline() {
        return "Copy Menu";
    }
}
