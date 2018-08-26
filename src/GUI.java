import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JLabel searchPanelLabel, UCASCodeLabel, placesPanelLabel, remainingPlacesLabel;
    private JTextField UCASCourseCode, remainingPlacesField;
    private JButton searchButton, placeOfferedButton;

    public GUI() {
        searchPanelLabel = new JLabel("Enter the UCAS Course Code and hit 'Search' to find a course.");
        UCASCodeLabel = new JLabel("UCAS Code");
        UCASCourseCode = new JTextField(10);
        searchButton = new JButton("Search");
        placesPanelLabel = new JLabel("Select 'Place Offered' to decrease remaining places by one.");
        remainingPlacesLabel = new JLabel("Remaining Places");
        remainingPlacesField = new JTextField(10);
        placeOfferedButton = new JButton("Place Offered");

        Dimension panelDimensions = new Dimension(400, 150);

        // Top Panel - contains search facility to find course
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchPanelLabel);
        searchPanel.add(UCASCodeLabel);
        searchPanel.add(UCASCourseCode);
        searchPanel.add(searchButton);
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        searchPanel.setPreferredSize(panelDimensions);

        // Bottom Panel - contains information about places remaining on a course
        JPanel placesPanel = new JPanel();
        placesPanel.add(placesPanelLabel);
        placesPanel.add(remainingPlacesLabel);
        placesPanel.add(remainingPlacesField);
        placesPanel.add(placeOfferedButton);
        placesPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        placesPanel.setPreferredSize(panelDimensions);

        // JFrame settings
        this.setTitle("Clearing Places Updates");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.add(searchPanel, BorderLayout.CENTER);
        this.add(placesPanel, BorderLayout.SOUTH);
    }

    public String getUCASCourseCode() {
        return UCASCourseCode.getText();
    }

    public void setRemainingPlacesField(int remainingPlaces) {
        remainingPlacesField.setText(String.valueOf(remainingPlaces));
    }

    public void addSearchListener(ActionListener searchListener) {
        searchButton.addActionListener(searchListener);
    }

    public void addPlacesListener(ActionListener searchListener) {
        placeOfferedButton.addActionListener(searchListener);
    }
}

