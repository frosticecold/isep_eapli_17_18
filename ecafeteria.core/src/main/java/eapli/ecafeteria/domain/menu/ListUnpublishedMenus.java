/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class ListUnpublishedMenus {

    /**
     * Unordered list of critical Menus
     */
    private List<Menu> criticalMenus;
    /**
     * Unordered list of non critical Menus
     */
    private List<Menu> notCriticalMenus;

    /**
     * Private empty constructor, this object does not exist empty
     */
    private ListUnpublishedMenus() {
    }

    /**
     * Constructor of a ListUnpublishedMenus that receives an Iterable of Menu
     * It constructos the inner lists according to critical/non critical
     * deadline menu times
     *
     * @param resultOfDB Result of DB query
     */
    public ListUnpublishedMenus(Iterable<Menu> resultOfDB) {
        criticalMenus = new LinkedList<>();
        notCriticalMenus = new LinkedList<>();
        addAll(resultOfDB);

    }

    /**
     * Adds all results to the respective list
     *
     * @param resultOfDB
     */
    private void addAll(Iterable<Menu> resultOfDB) {
        for (Menu m : resultOfDB) {
            add(m);
        }
    }

    /**
     * Add a menu to the critical or noncritical lists
     *
     * @param menu Menu to add
     */
    public void add(Menu menu) {
        if (menu.isCritical()) {
            criticalMenus.add(menu);
        } else {
            notCriticalMenus.add(menu);
        }
    }

    /**
     * Returns an iterable of critical menus
     *
     * @return
     */
    public Iterable<Menu> criticalMenus() {
        return criticalMenus;
    }

    /**
     * Returns an iterable of non critical menus
     *
     * @return
     */
    public Iterable<Menu> notCriticalMenus() {
        return notCriticalMenus;
    }
}
