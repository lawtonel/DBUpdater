/** Unit tests to check functionality of QueryManager and DBController
    Assumes the following as linked with external database:
    UCAS Code G400 > 20 places remaining
    UCAS Code H601 = 0 places remaining
    UCAS Code H300 = 15 places remaining
    There are 3 courses on the Clearing Database **/

package Test;

import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TestQueryManager {
    QueryManager queryManager;

    // Setup the queryManager object for use in this test suite
    @BeforeEach
    public void setup() {
        queryManager = new QueryManager();
    }

    @Test
    public void testGetAllCourses() {
        ArrayList<String> testCourseList = queryManager.getAllCourses();
        assertTrue(testCourseList.contains("G400"));
        assertFalse(testCourseList.contains("Not_A_Course"));
    }

    @Test
    public void testGetAllCoursesListSize() {
        ArrayList<String> testCourseList = queryManager.getAllCourses();
        assertEquals(3, testCourseList.size());
    }

    @Test
    public void testGetCoursesInClearingList() {
        ArrayList<String> coursesList = queryManager.getAllCourses();
        assertEquals(coursesList, queryManager.getCoursesInClearing());
    }

    @Test
    public void testGetRemainingPlacesInitial() {
        assertEquals(0, queryManager.getRemainingPlaces());
    }

    @Test
    public void testGetRemainingPlacesAfterQuery() {
        assertEquals(0, queryManager.getRemainingPlaces());
        queryManager.retrieveRemainingPlaces("H300");
        assertEquals(15, queryManager.getRemainingPlaces());
    }

    // Test that once executed, the number of places on a valid course decreases by one
    @Test
    public void testSuccessfulDecreasePlaces() {
        int initPlaces = queryManager.retrieveRemainingPlaces("G400");
        queryManager.decreaseNoPlaces("G400");
        assertEquals(queryManager.retrieveRemainingPlaces("G400"), (initPlaces-1));
    }

    /* Test that once a valid course reaches 0 places, the number does not decrease
        i.e. minus numbers not accepted*/
    @Test
    public void testNoPlacesLeft() {
        assertEquals(0, queryManager.retrieveRemainingPlaces("H601"));
        queryManager.decreaseNoPlaces("H601");
        assertEquals(0, queryManager.retrieveRemainingPlaces("H601"));
    }

    @Test
    public void testInvalidInput() {

    }

    // Clears the list of courses created on initialisation
    @AfterEach
    public void tearDown() {
        queryManager.getCoursesInClearing().clear();
    }
}
