/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.domain.dishes.AlergenEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author utilizador
 */
@Entity
public class AlergenProfile implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private List<Alergen> alergenList;
    @OneToOne
    private CafeteriaUser user;

    public AlergenProfile(CafeteriaUser user) {
        this.user = user;
        alergenList = new ArrayList<>();
    }

    protected AlergenProfile() {
        //FOR ORM
    }

    public List<Alergen> alergens() {
        return alergenList;
    }

    public boolean addAlergen(Alergen a) {
        return alergenList.add(a);
    }

    public boolean removeAlergen(Alergen a) {
        return alergenList.remove(a);
    }

}
