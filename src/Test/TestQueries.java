package Test;

import main.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestQueries {

    @Test
    public void testSuccesfulDecreasePlaces() {
        QueryManager queryManager = new QueryManager();
        int initialPlaces = queryManager.retrieveRemainingPlaces("G400");
        assertEquals(queryManager.decreaseNoPlaces("G400"), (initialPlaces-1));
    }

    @Test
    public void testNoPlacesLeft() {
        QueryManager queryManager = new QueryManager();

    }
}
