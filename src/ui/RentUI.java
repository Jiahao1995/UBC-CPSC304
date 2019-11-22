package ui;

import delegates.RentDelegate;
import delegates.ReservationDelegate;

import javax.swing.*;
import java.awt.*;

public class RentUI extends JFrame {
    private static final int TEXT_FIELD_WIDTH = 20;

    private RentDelegate delegate;

    private JTextField typeField;
    private JTextField phoneField;
    private JTextField fromDateField;
    private JTextField fromTimeField;
    private JTextField toDateField;
    private JTextField toTimeField;
    private JTextField cardField;

    public RentUI() {
        super("Reserve");
    }

    public void showFrame(RentDelegate delegate) {
        this.delegate = delegate;

        JLabel typeLabel = new JLabel("Vehicle Type: ");
        JLabel phoneLabel = new JLabel("Phone: ");
        JLabel fromDateLabel = new JLabel("From Date: ");
        JLabel fromTimeLabel = new JLabel("From Time: ");
        JLabel toDateLabel = new JLabel("To Date: ");
        JLabel toTimeLabel = new JLabel("To Time: ");
        JLabel cardLabel = new JLabel("Card Number: ");

        typeField = new JTextField(TEXT_FIELD_WIDTH);
        phoneField = new JTextField(TEXT_FIELD_WIDTH);
        fromDateField = new JTextField(TEXT_FIELD_WIDTH);
        fromTimeField = new JTextField(TEXT_FIELD_WIDTH);
        toDateField = new JTextField(TEXT_FIELD_WIDTH);
        toTimeField = new JTextField(TEXT_FIELD_WIDTH);
        cardField = new JTextField(TEXT_FIELD_WIDTH);

        JButton viewButton = new JButton("Rent");

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        // place the type label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(typeLabel, c);
        contentPane.add(typeLabel);

        // place the type field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(typeField, c);
        contentPane.add(typeField);

        // place the phone label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(phoneLabel, c);
        contentPane.add(phoneLabel);

        // place the phone field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(phoneField, c);
        contentPane.add(phoneField);

        // place the from date label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(fromDateLabel, c);
        contentPane.add(fromDateLabel);

        // place the from date field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(fromDateField, c);
        contentPane.add(fromDateField);

        // place the from time label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(fromTimeLabel, c);
        contentPane.add(fromTimeLabel);

        // place the from time field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(fromTimeField, c);
        contentPane.add(fromTimeField);

        // place the to date label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(toDateLabel, c);
        contentPane.add(toDateLabel);

        // place the to date field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(toDateField, c);
        contentPane.add(toDateField);

        // place the to time label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(toTimeLabel, c);
        contentPane.add(toTimeLabel);

        // place the to time field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(toTimeField, c);
        contentPane.add(toTimeField);

        // place the card label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(cardLabel, c);
        contentPane.add(cardLabel);

        // place the card field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(cardField, c);
        contentPane.add(cardField);

        // place the view button
        c.gridwidth = GridBagConstraints.EAST;
        c.insets = new Insets(20, 0, 0, 10);
        gb.setConstraints(viewButton, c);
        contentPane.add(viewButton);

        // register customer button with action event handler
        viewButton.addActionListener(e -> this.delegate.rentReserve(
                typeField.getText(),
                phoneField.getText(),
                fromDateField.getText(),
                fromTimeField.getText(),
                toDateField.getText(),
                toTimeField.getText(),
                cardField.getText()
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
        ReservationUI reservationUI = new ReservationUI();
        reservationUI.showFrame(new ReservationDelegate() {
            @Override
            public void reserve(String type, String fromDate, String fromTime, String toDate, String toTime) {

            }
        });
    }
}