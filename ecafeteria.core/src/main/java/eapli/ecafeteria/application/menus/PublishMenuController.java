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
    
    //private final ListMenuService svc = new ListMenuService();
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final ListUnpublishedMenus m_list = new ListUnpublishedMenus(menuRepository.listValidMenus());
    
    public Iterable<Menu> criticalMenus() {
        return m_list.criticalMenus();
    }
    
    public Iterable<Menu> notCriticalMenus() {
        return m_list.notCriticalMenus();
    }
    
    public final String publishCriticalMenus() throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if(criticalMenus() == null) {
            throw new IllegalArgumentException();
        }
        return publishMenus(criticalMenus());
    }
    
    public final String publishNotCriticalMenus() throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if(notCriticalMenus() == null) {
            throw new IllegalArgumentException();
        }
        return publishMenus(notCriticalMenus());
    }
    
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
