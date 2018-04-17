/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class RatingTest {

    private CafeteriaUser user;
    private Meal meal;

    public RatingTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        Calendar date = getNextMonday();
//        Set<RoleType> roleType = new HashSet<>();
//        roleType.add(RoleType.CAFETERIA_USER);
//        user = new CafeteriaUser(
//                new SystemUser("User", "Pass23", "Nome", "LNome", "email@email.com", roleType),
//                new MecanographicNumber("111")
//        );
//        meal = new Meal(
//                new Dish(
//                        new DishType("vegie", "vegetarian dish"),
//                        Designation.valueOf("a dish of vegetarian"),
//                        Money.euros(17.80)), MealType.DINNER, date);

    }

//    private Calendar getNextMonday() {
//        Calendar date = DateTime.now();
//        while (date.get(Calendar.DAY_OF_WEEK) != 1) {
//            date.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        return date;
//    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void sureRatingHasValidBooking() {
        new Rating(null, 4, "Good meal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void sureRatingHasValidRating() {
        new Rating(new Booking(meal, user), 10, "Good meal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void sureRatingHasValidComment() {
        new Rating(new Booking(meal, user), 3, null);
    }

//    /**
//     * Test of equals method, of class Rating.
//     */
//    @Test
//    public void testEquals() {
//        Booking booking1 = new Booking(meal, user);
//
//        CafeteriaUser user2 = new CafeteriaUser(
//                new SystemUser("User", "Pass23", "Nome", "LNome", "email@email.com", new HashSet<>()),
//                new MecanographicNumber("111")
//        );
//        Booking booking2 = new Booking(meal, user2);
//
//        Rating rating1 = new Rating(booking1, 4, "Good Meal");
//        Rating rating2 = new Rating(booking2, 4, "Good Meal");
//
//        assertTrue(rating1.equals(rating2));
//
//        booking2 = new Booking(meal, user);
//        rating2 = new Rating(booking2, 5, "Good Meal");
//
//        assertFalse(rating1.equals(rating2));
//    }
}
//
//    /**
//     * Test of hashCode method, of class Rating.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Rating instance = new Rating();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of sameAs method, of class Rating.
//     */
//    @Test
//    public void testSameAs() {
//        System.out.println("sameAs");
//        Object other = null;
//        Rating instance = new Rating();
//        boolean expResult = false;
//        boolean result = instance.sameAs(other);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of id method, of class Rating.
//     */
//    @Test
//    public void testId() {
//        System.out.println("id");
//        Rating instance = new Rating();
//        Long expResult = null;
//        Long result = instance.id();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of is method, of class Rating.
//     */
//    @Test
//    public void testIs() {
//        System.out.println("is");
//        Long otherId = null;
//        Rating instance = new Rating();
//        boolean expResult = false;
//        boolean result = instance.is(otherId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Rating.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Rating instance = new Rating();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

