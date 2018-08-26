public class UpdateProgram {

    public static void main(String args[]) {
        Connection connection = new Connection();
        GUI gui = new GUI();
        DBController controller = new DBController(connection, gui);

        gui.setVisible(true);
    }


}
