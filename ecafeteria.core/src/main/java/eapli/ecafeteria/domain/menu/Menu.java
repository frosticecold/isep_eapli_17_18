/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Menu {

    /**
     * Menu state of this Menu It has two possible 
     * By default when a menu is created, it is unpublished
     * <p>
     * # Published
     * <p>
     * # Unpublished
     *
     */
    private MenuState menuState;

    /**
     * Planned period of a menu, starting day is on a Monday and ending day is
     * on a Sunday. Seven working days total planned
     */
    private Period plannedPeriod;

    /**
     *
     *
     * @param startingDayOfWeek Starting working day of a week in format
     * dd-MM-yyyy
     * @param endingDayOfWeek Starting working day of a week in format
     * dd-MM-yyyy
     */
    public Menu(final String startingDayOfWeek, final String endingDayOfWeek) {
        menuState = MenuState.UNPUBLISHED;
    }

    private void setPeriod(final String startingDayOfWeek, final String endingDayOfWeek) {
        
        
    }
}
