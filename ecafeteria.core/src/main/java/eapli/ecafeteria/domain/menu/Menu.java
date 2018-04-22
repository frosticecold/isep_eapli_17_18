package eapli.ecafeteria.domain.menu;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
@Entity
public class Menu implements AggregateRoot<Period>, Serializable {

    /*
    ============================================================================
                                    Variables
    ============================================================================
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idmenu")
    private Long id;

    @Version
    private Long version;
    /**
     * Menu state of this Menu It has two possible By default when a menu is
     * created, it is unpublished
     * <p>
     * # Published
     * <p>
     * # Unpublished
     *
     */
    @Column(name = "menustate")
    private MenuState menuState;

    /**
     * Planned period of a menu, starting day is on a Monday and ending day is
     * on a Sunday. Seven working days total planned
     */
    @Embedded
    private Period period;

    /*
    ============================================================================
                                    Functions
    ============================================================================
     */
    /**
     * Protected Empty constructor ORM
     */
    protected Menu() {
    }

    /**
     *
     * @author Raúl Correia
     * @param startingDayOfWeek Starting working day of a week in format
     * dd-MM-yyyy
     * @param endingDayOfWeek Starting working day of a week in format
     * dd-MM-yyyy
     */
    public Menu(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        menuState = MenuState.UNPUBLISHED;
        setPeriod(startingDayOfWeek, endingDayOfWeek);
    }

    /**
     * @author Raúl Correia
     * @param startingOfWeek
     * @param endOfWeek
     *
     */
    public Menu(final Calendar startingOfWeek, final Calendar endOfWeek) throws IllegalArgumentException {
        menuState = MenuState.UNPUBLISHED;
        period = new Period(startingOfWeek, endOfWeek);
    }

    public Period period() {
        return period;
    }

    /*
    ============================================================================
                                    Private Functions
    ============================================================================
     */
    /**
     * Method that creates a period and saves as a member variable Throws
     * IllegalArgumentException in case something goes wrong
     *
     * @author Raúl Correia
     * @param startingDayOfWeek
     * @param endingDayOfWeek
     */
    private void setPeriod(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        period = new Period(startingDayOfWeek, endingDayOfWeek);
    }

    /*
    ============================================================================
                                    Public Functions
    ============================================================================
     */
    /**
     * Get a map with all the calenders(dates) between the start and ending date
     *
     * @return
     */
    public Map<Integer, Calendar> getWorkWeekDays() {
        return period.getWorkingDays();
    }

    public Iterable<Calendar> getWorkWeekDaysIterable() {
        return period.getWorkingDaysIterable();
    }

    /**
     * Method that returns if the menu is critical or not
     *
     * @author Raúl Correia
     * @return true if critical by business logic , false ifnot
     */
    public boolean isCritical() {
        return period.isCritical();
    }

    /**
     * Methot that returns if a method is published
     * @return 
     */

    public boolean isPublished() {
        return menuState == MenuState.PUBLISHED;
    }

    /**
     * Method that changes the state of a menu to published
     *
     * @author Raúl Correia
     * @return true if the menu is published, false if is already published
     */
    public boolean publish() {
        if (menuState == MenuState.PUBLISHED) {
            return false;
        }
        menuState = MenuState.PUBLISHED;
        return true;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Menu)) {
            return false;
        }
        final Menu othermenu = (Menu) other;
        if (this == othermenu) {
            return true;
        }
        return this.period.equals(othermenu.period);
    }

    @Override
    public Period id() {
        return period;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Menu other = (Menu) obj;
        if (!Objects.equals(this.period, other.period)) {
            return false;
        }
        return true;
    }

}
