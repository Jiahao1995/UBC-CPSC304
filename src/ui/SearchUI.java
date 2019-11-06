package ui;

import delegates.SearchDelegate;

import javax.swing.*;
import java.awt.*;

public class SearchUI extends JFrame {

    // components of the search window
    private String carType;
    private String location;
    private String fromDate;
    private String fromTime;
    private String toDate;
    private String toTime;

    // delegate
    private SearchDelegate delegate;

    public SearchUI() {
        super("Find Your Car");
    }

    public void showFrame(SearchDelegate delegate) {
        this.delegate = delegate;

        JLabel from = new JLabel("From");
        JLabel to = new JLabel("To");

        JLabel carType = new JLabel("Car Type: ");
        JLabel location = new JLabel("Location: ");

        JTextField fromDate = new JTextField(10);
        fromDate.setText("YYYY-MM-DD");
        JTextField fromTime = new JTextField(5);
        fromTime.setText("HH:MM");
        JTextField toDate = new JTextField(10);
        toDate.setText("YYYY-MM-DD");
        JTextField toTime = new JTextField(5);
        toTime.setText("HH:MM");

        JComboBox carTypesBox = new JComboBox();
        carTypesBox.addItem("--Select--");

        JComboBox locationsBox = new JComboBox();
        locationsBox.addItem("--Select--");

        JButton cancelButton = new JButton("Cancel");
        JButton resetButton = new JButton("Reset");
        JButton searchButton = new JButton("Search");

        this.carType = carTypesBox.getSelectedItem().toString();
        this.location = locationsBox.getSelectedItem().toString();
        this.fromDate = fromDate.getText();
        this.fromTime = fromTime.getText();
        this.toDate = toDate.getText();
        this.toTime = toTime.getText();

        JPanel contentPanel = new JPanel();
        this.setContentPane(contentPanel);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPanel.setLayout(gb);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // place the from label
        c.gridwidth = GridBagConstraints.WEST;
        c.insets = new Insets(0, 100, 0, 10);
        gb.setConstraints(from, c);
        contentPanel.add(from);

        // place the fromDate label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(fromDate, c);
        contentPanel.add(fromDate);

        // place the fromTime label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 100);
        gb.setConstraints(fromTime, c);
        contentPanel.add(fromTime);

        // place the to label
        c.gridwidth = GridBagConstraints.WEST;
        c.insets = new Insets(0, 100, 0, 10);
        gb.setConstraints(to, c);
        contentPanel.add(to);

        // place the toDate label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(toDate, c);
        contentPanel.add(toDate);

        // place the toTime label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 100);
        gb.setConstraints(toTime, c);
        contentPanel.add(toTime);

        // place the carTypes label
        c.gridwidth = GridBagConstraints.WEST;
        c.insets = new Insets(10, 50, 0, 0);
        gb.setConstraints(carType, c);
        contentPanel.add(carType);

        // place the carTypes combo box
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 0, 20);
        gb.setConstraints(carTypesBox, c);
        contentPanel.add(carTypesBox);

        // place the location label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 0, 15);
        gb.setConstraints(location, c);
        contentPanel.add(location);

        // place the locations combo box
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 0, 50);
        gb.setConstraints(locationsBox, c);
        contentPanel.add(locationsBox);

        // place the cancel button
        c.gridwidth = GridBagConstraints.WEST;
        c.insets = new Insets(20, 100, 0, 0);
        gb.setConstraints(cancelButton, c);
        contentPanel.add(cancelButton);

        // place the reset button
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 0, 0, 0);
        gb.setConstraints(resetButton, c);
        contentPanel.add(resetButton);

        // place the search button
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 0, 0, 0);
        gb.setConstraints(searchButton, c);
        contentPanel.add(searchButton);

        // register buttons with action event handlers
        cancelButton.addActionListener(e -> this.dispose());
        resetButton.addActionListener(e -> this.delegate.reset());
        searchButton.addActionListener(e -> this.delegate.search(
                this.fromDate,
                this.fromTime,
                this.toDate,
                this.toTime,
                this.carType,
                this.location
        ));

        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

        // make the window visible
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SearchUI searchUI = new SearchUI();
        searchUI.showFrame(new SearchDelegate() {
            @Override
            public void search(String fromDate, String fromTime, String toDate, String toTime, String carType, String location) {

            }

            @Override
            public void reset() {

            }
        });
    }


}
