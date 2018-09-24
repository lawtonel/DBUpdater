package main;

public class DBUpdateProgram {

    public static void main(String args[]) {
        QueryManager QueryManager = new QueryManager();
        GUI gui = new GUI();
        DBController controller = new DBController(QueryManager, gui);

        gui.setVisible(true);
    }


}
