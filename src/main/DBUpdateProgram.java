package main;

public class DBUpdateProgram {

    public static void main(String args[]) {
        QueryManager queryManager = new QueryManager();
        GUI gui = new GUI();
        DBController controller = new DBController(queryManager, gui);
        queryManager.addObserver(gui);
        gui.setVisible(true);
    }


}
