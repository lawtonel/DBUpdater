package Test;

import main.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestQueries {

    @Test
    public void testGetAllCourses() {
        QueryManager queryManager = new QueryManager();
        ArrayList<String> testCourseList = queryManager.getCoursesInClearing();
        assertTrue(testCourseList.contains("G400"));
        assertTrue(testCourseList.contains("H601"));
        assertFalse(testCourseList.contains("Not_A_Course"));
    }

    @Test
    public void testGetAllCoursesListSize() {
        QueryManager queryManager = new QueryManager();
        ArrayList<String> testCourseList = queryManager.getCoursesInClearing();
        assertEquals(2, testCourseList.size());
    }

    @Test
    public void testSuccesfulDecreasePlaces() {
        QueryManager queryManager = new QueryManager();
        int initialPlaces = queryManager.retrieveRemainingPlaces("G400");
        assertEquals(queryManager.decreaseNoPlaces("G400"), (initialPlaces-1));
    }

    @Test
    public void testNoPlacesLeft() {
        QueryManager queryManager = new QueryManager();
        queryManager.decreaseNoPlaces("H601");
        assertEquals(0, queryManager.retrieveRemainingPlaces("H601"));
    }
}
