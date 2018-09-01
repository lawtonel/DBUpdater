package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBController {
    private GUI gui;
    private QueryManager QueryManager;

    public DBController(QueryManager QueryManager, GUI gui) {
        this.QueryManager = QueryManager;
        this.gui = gui;
        this.gui.addSearchListener(new SearchListener());
        this.gui.addPlacesListener(new PlacesListener());
    }

    public class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseCode = gui.getUCASCourseCode();
            if(courseCode.equals("")) {
                gui.showErrorMessage("Please enter a UCAS Code.");
            } else {
                gui.setRemainingPlacesField(QueryManager.retrieveRemainingPlaces(courseCode));
            }
        }
    }

    public class PlacesListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseCode = gui.getUCASCourseCode();
            if(QueryManager.retrieveRemainingPlaces(courseCode) <= 0) {
                gui.showErrorMessage("There are no places left on this course.");
            }
            gui.setRemainingPlacesField(QueryManager.decreaseNoPlaces(courseCode));
        }
    }
}
