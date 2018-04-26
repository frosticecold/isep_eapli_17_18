/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.menu.ListUnpublishedMenus;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class PublishMenuController implements Controller {
    
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final ListUnpublishedMenus m_list = new ListUnpublishedMenus(menuRepository.listValidMenus());
    
    /**
     * List of critical menus
     * 
     * @return list of critical menus
     */
    public Iterable<Menu> criticalMenus() {
        return m_list.criticalMenus();
    }
    
    /**
     * List of not critical menus
     * 
     * @return list of not critical menus
     */
    public Iterable<Menu> notCriticalMenus() {
        return m_list.notCriticalMenus();
    }
    
    /**
     * Publishes critical menus having in consideration the user logged in
     * @return statistics of published menus
     * 
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    public final String publishCriticalMenus() throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if(criticalMenus() == null) {
            throw new IllegalArgumentException();
        }
        return publishMenus(criticalMenus());
    }
    
    /**
     * Publishes not critical menus having in consideration the user logged in
     * @return statistics of published menus
     * 
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    public final String publishNotCriticalMenus() throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if(notCriticalMenus() == null) {
            throw new IllegalArgumentException();
        }
        return publishMenus(notCriticalMenus());
    }
    
    /**
     * Generic method to publish a list of menus and print the number of published and unpublished menus
     * 
     * @param menus menus to publish
     * 
     * @return String with statistics
     * 
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    private String publishMenus(Iterable<Menu> menus) throws DataConcurrencyException, DataIntegrityViolationException {
        int published = 0;
        int unpublished = 0;
        
        for (Menu menu : menus) {
            if(menu.publish()) {
                published++;
            } else {
                unpublished++;
            }
            menuRepository.save(menu);
        }
        return String.format("Published Menus: %d\nUnpublished Menus: %d\n", published, unpublished);
    }
    
}
