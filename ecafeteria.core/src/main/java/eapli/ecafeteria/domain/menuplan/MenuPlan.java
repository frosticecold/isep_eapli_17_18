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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class MenuPlan implements AggregateRoot<Long>,Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_menu_plan")
    private Long id;
    
    @Version
    private Long version;  
    
    @OneToOne
    private Menu selectedMenu;
    
    private boolean closed;
    
    protected MenuPlan(){
    
    }

    public MenuPlan(Menu menu) {
        this.selectedMenu = menu;
        closed=false;
    }

    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
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

    @Override
    public String toString() {
        return "MenuPlan{" + "id=" + id + ", version=" + version + ", selectedMenu=" + selectedMenu + ", closed=" + closed + '}';
    }
    
   
   
}
