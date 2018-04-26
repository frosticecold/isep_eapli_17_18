/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meal.Meal;
import org.junit.After;
import org.junit.AfterClass;
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

    }

    @After
    public void tearDown() {
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void makeSureRatingAlwaysHasAValidBooking() {
//        new Rating(null, 4, "Good meal");
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void sureRatingHasValidRating() {
//        new Rating(new Booking(), 10, "Good meal");
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void sureRatingHasValidComment() {
//        new Rating(new Booking(), 3, null);
//    }
}
