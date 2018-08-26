import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBController {
    private GUI gui;
    private Connection connection;

    public DBController(Connection connection, GUI gui) {
        this.connection = connection;
        this.gui = gui;
        this.gui.addSearchListener(new SearchListener());
        this.gui.addPlacesListener(new PlacesListener());
    }

    public class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseCode = gui.getUCASCourseCode();
            gui.setRemainingPlacesField(connection.retrieveRemainingPlaces(courseCode));
        }
    }

    public class PlacesListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseCode = gui.getUCASCourseCode();
            gui.setRemainingPlacesField(connection.decreaseNoPlaces(courseCode));
        }
    }
}
