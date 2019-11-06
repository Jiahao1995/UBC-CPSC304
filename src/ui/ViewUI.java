package ui;

import delegates.ViewDelegate;

import javax.swing.*;
import java.awt.*;

public class ViewUI extends JFrame {

    // components of the sign up window
    private int numAvailableVehicles;
    private String location;
    private String fromDate;
    private String fromTime;

    // delegate
    private ViewDelegate delegate;

    public ViewUI() {
        super("View Available Vehicles");
    }

    public void showFrame(ViewDelegate delegate) {
        this.delegate = delegate;

        JLabel resultLabel = new JLabel("There are " + numAvailableVehicles + " vehicles available:");

        String[][] content = new String[10][9];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                content[i][j] = "1";
            }
        }
        String[] name = { "ID", "Make", "Model", "Year", "Color", "Odometer", "Type", "Location", "City" };
        JTable resultTable = new JTable(content, name);
        JScrollPane resultPane = new JScrollPane(resultTable);

        JButton selectButton = new JButton("Select");
        JButton cancelButton = new JButton("Cancel");

        JPanel contentPanel = new JPanel();
        this.setContentPane(contentPanel);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPanel.setLayout(gb);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // place the result label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 200, 10, 200);
        gb.setConstraints(resultLabel, c);
        contentPanel.add(resultLabel);

        // place the result table
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(resultPane, c);
        contentPanel.add(resultPane);

        // place the cancel button
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 100, 0, 30);
        gb.setConstraints(cancelButton, c);
        contentPanel.add(cancelButton);

        // place the select button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 100);
        gb.setConstraints(selectButton, c);
        contentPanel.add(selectButton);

        // register buttons with action event handlers
        cancelButton.addActionListener(e -> this.dispose());
        selectButton.addActionListener(e -> this.delegate.select());

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
        ViewUI viewUI = new ViewUI();
        viewUI.showFrame(new ViewDelegate() {
            @Override
            public void select() {

            }
        });
    }

}
