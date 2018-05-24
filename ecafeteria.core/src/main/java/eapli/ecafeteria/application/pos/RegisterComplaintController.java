/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.pos.Complaint;
import eapli.ecafeteria.domain.pos.DescriptiveText;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;

/**
 *
 * @author Telmo
 */
public class RegisterComplaintController implements Controller{
    private static final ComplaintRepository complaintRepository = PersistenceContext.repositories().complaints();
    private static final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    private static final DishRepository dishRepository = PersistenceContext.repositories().dishes();
    private Dish dish;
    private CafeteriaUser user;
    
    public void setUser(CafeteriaUser user){
        this.user = user;
    }
    
    public void setDish(Dish dish){
        this.dish = dish;
    }
    
    /**
     * Method that saves a complaint on data base
     * @param description description of complaint
     * @param user user of complaint
     * @param dish dish of complaint
     * @return the complaint to save
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    public Complaint saveComplaint(DescriptiveText description) throws DataConcurrencyException, DataIntegrityViolationException{
        Complaint c = new Complaint(description,dish ,user);
        return complaintRepository.save(c);
    }
    
    /**
     * 
     * @param id macanographic number of user
     * @return  cafeteria user
     */
    public Optional<CafeteriaUser> getUserById(MecanographicNumber id){
        return cafeteriaUserRepository.findByMecanographicNumber(id);
    }
    
    /**
     * @return the list of dishes
     */
    public Iterable<Dish> getDishs(){
        return dishRepository.findAll();
    }
}
