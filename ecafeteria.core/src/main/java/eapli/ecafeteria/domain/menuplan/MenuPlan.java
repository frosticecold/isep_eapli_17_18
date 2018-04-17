/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menuplan;


import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class MenuPlan implements AggregateRoot<Long>,Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_menu_plan")
    private long id;
    
    @Version
    private Long version;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<MenuPlanItem> menuPlanItemList;
    
    private Menu selectedMenu;
    
    private boolean closed;
    
    protected MenuPlan(){
    
    }

    public MenuPlan(List<MenuPlanItem> menuPlanItemList, Menu menu) {
        this.menuPlanItemList = menuPlanItemList;
        this.selectedMenu = menu;
        closed=false;
    }

    @Override
    public boolean sameAs(Object other) {
        if(!(other instanceof MenuPlan )){
            return false;
        }
        final MenuPlan m=(MenuPlan) other;
        
        if(this==m){
            return true;
        }
        
        return this.id==m.id();
    }
    
    @Override
    public Long id() {
        return id;
    }
   
}