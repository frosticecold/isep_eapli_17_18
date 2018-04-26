/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meal;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
@Entity
public class Meal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;

    /**
     * Dish of a meal
     */
    @OneToOne()
    @JoinColumn()
    private Dish dish;

    /**
     * Type of a meal
     */
    private MealType mealtype;
    /**
     * Date of a Meal
     *
     * @author Raúl Correia
     */
    @Temporal(TemporalType.DATE)
    private Calendar date;
    /*
    * Ratings of the meal
     */
    @OneToMany()
    private List<Rating> ratings;

    /**
     * Menu that a meal belongs to
     */
    @OneToOne()
    @JoinColumn()
    private Menu menu;

    /**
     * For ORM
     */
    protected Meal() {

    }

    /**
     * Constructor for a Meal
     *
     * @param dish Dish for this meal
     * @param mt Mealtype for this meal
     * @param cal Date for this meal
     * @param menu Menu where this meal is inserted
     */
    public Meal(final Dish dish, final MealType mt, final Calendar cal, final Menu menu) {
        setData(dish, mt, cal, menu);
    }

    private void setData(final Dish dish, final MealType mt, final Calendar cal, final Menu menu) {
        if (dish == null || mt == null || cal == null || menu == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        }
        this.dish = dish;
        this.mealtype = mt;
        this.date = (Calendar) cal.clone();
        this.menu = menu;
    }

    public boolean isOnGivenDate(Calendar givenDate) {
        return DateTime.isSameDate(givenDate, date);
    }

    public Long id() {
        return id;
    }

    public Dish dish() {
        return dish;
    }

    public Menu menu() {
        return menu;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.version);
        hash = 59 * hash + Objects.hashCode(this.dish);
        hash = 59 * hash + Objects.hashCode(this.mealtype);
        hash = 59 * hash + Objects.hashCode(this.date);
        hash = 59 * hash + Objects.hashCode(this.ratings);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meal other = (Meal) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dish, other.dish)) {
            return false;
        }
        if (this.mealtype != other.mealtype) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    /**
     * Returns Meal actual date
     *
     * @return
     */
    public Calendar getMealDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String strDate = DateTime.convertCalendarToDayMonthYearAndDayName(date);
        return "Meal: "+id+", dish=" + dish + ", mealtype=" + mealtype + ", date=" + strDate + '}';
    }

    /**
     * Returns the ratings given on said meal
     *
     * @return
     */
    public Iterable<Rating> ratings() {
        return this.ratings;
    }

    public MealType mealtype() {
        return mealtype;
    }

}
