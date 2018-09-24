package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBController {
    private GUI gui;
    private QueryManager queryManager;

    public DBController(QueryManager queryManager, GUI gui) {
        this.queryManager = queryManager;
        this.gui = gui;
        this.gui.addSearchListener(new SearchListener());
        this.gui.addPlacesListener(new PlacesListener());
    }

    public class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseCode = gui.getUCASCourseCode().toUpperCase();
            if(courseCode.equals("")) {
                gui.showErrorMessage("Please enter a UCAS Code.");
            } else if(!queryManager.getCoursesInClearing().contains(courseCode)) {
                gui.showErrorMessage("Please enter a valid UCAS code");
            } else {
                gui.setRemainingPlacesField(queryManager.retrieveRemainingPlaces(courseCode));
            }
        }
    }

    public class PlacesListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseCode = gui.getUCASCourseCode();
            if(courseCode.equals("")) {
                gui.showErrorMessage("Please enter a valid UCAS code");
            } else if(queryManager.retrieveRemainingPlaces(courseCode) <= 0) {
                gui.showErrorMessage("There are no places left on this course.");
            } else {
                gui.setRemainingPlacesField(queryManager.decreaseNoPlaces(courseCode));
            }
        }
    }
}
