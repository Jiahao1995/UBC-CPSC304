package ui;

import delegates.ConfirmationDelegate;

import javax.swing.*;
import java.awt.*;

public class ConfirmationUI extends JFrame {

    private ConfirmationDelegate delegate;

    private String type;
    private String fromDate;
    private String fromTime;
    private String toDate;
    private String toTime;

    public ConfirmationUI(
            String type,
            String fromDate,
            String fromTime,
            String toDate,
            String toTime) {
        super("Confirmation");
        this.type = type;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    public void showFrame(ConfirmationDelegate delegate) {
        this.delegate = delegate;

        JLabel typeLabel = new JLabel("Vehicle Type: " + type);
        JLabel fromLabel = new JLabel("From: " + fromDate + " " + fromTime);
        JLabel toLabel = new JLabel("To: " + toDate + " " + toTime);

        JButton button = new JButton("Confirm");

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

//        // place the id label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(idLabel, c);
//        contentPane.add(idLabel);
//
//        // place the license label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(licenseLabel, c);
//        contentPane.add(licenseLabel);

        // place the type label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(typeLabel, c);
        contentPane.add(typeLabel);

        // place the from label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(fromLabel, c);
        contentPane.add(fromLabel);

        // place the to label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(toLabel, c);
        contentPane.add(toLabel);

//        // place the make label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(makeLabel, c);
//        contentPane.add(makeLabel);
//
//        // place the model label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(modelLabel, c);
//        contentPane.add(modelLabel);
//
//        // place the year label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(yearLabel, c);
//        contentPane.add(yearLabel);
//
//        // place the color label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(colorLabel, c);
//        contentPane.add(colorLabel);
//
//        // place the odometer label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(odometerLabel, c);
//        contentPane.add(odometerLabel);
//
//        // place the location label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(locationLabel, c);
//        contentPane.add(locationLabel);
//
//        // place the city label
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        c.insets = new Insets(0, 0, 0, 0);
//        gb.setConstraints(cityLabel, c);
//        contentPane.add(cityLabel);

        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20, 0, 0, 0);
        gb.setConstraints(button, c);
        contentPane.add(button);

        // register the button
        button.addActionListener(e -> this.delegate.confirm());

        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

        // make the window visible
        this.setVisible(true);
    }

}
