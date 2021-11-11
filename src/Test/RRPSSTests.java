package Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import Factory.RRPSS;
import Factory.Restaurant;

public class RRPSSTests {
    private static final String RESTAURANT_NAME = "Test Restaurant";

    // test creation of rrpss works
    @Test
    public void testRRPSS() {
        RRPSS rrpss = new RRPSS(RESTAURANT_NAME);
        assertNotNull(rrpss);
    }

    // test get name
    @Test
    public void testGetName() {
        RRPSS rrpss = new RRPSS(RESTAURANT_NAME);
        Restaurant restaurant = rrpss.getRestaurant();
        assertEquals(RESTAURANT_NAME, restaurant.getName());
    }

    // test creation of rrpss with null name
    @Test
    public void testRRPSSWithNullName() {
        RRPSS rrpss = new RRPSS(null);
        assertNotNull(rrpss);
    }

    // test creation of rrpss with empty name
    @Test
    public void testRRPSSWithEmptyName() {
        RRPSS rrpss = new RRPSS("");
        assertNotNull(rrpss);
    }

    // test creation of rrpss with whitespace name
    @Test
    public void testRRPSSWithWhitespaceName() {
        RRPSS rrpss = new RRPSS(" ");
        assertNotNull(rrpss);
    }

    // ! menu tests
    // test creation of rrpss with null menu
    

}
