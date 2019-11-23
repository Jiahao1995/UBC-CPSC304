package ui;

import delegates.DatabaseDelegate;

import javax.swing.*;
import java.awt.*;

public class DatabaseUI extends JFrame {

    private DatabaseDelegate delegate;

    public DatabaseUI() {
        super("Database");
    }

    public void showFrame(DatabaseDelegate delegate) {
        this.delegate = delegate;

        JButton vehicleTypeButton = new JButton("Vehicle Types");
        JButton vehicleButton = new JButton("Vehicles");
        JButton customerButton = new JButton("Customers");
        JButton reservationButton = new JButton("Reservations");
        JButton rentalButton = new JButton("Rentals");
        JButton returnButton = new JButton("Returns");

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // place the vehicle type button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(vehicleTypeButton, c);
        contentPane.add(vehicleTypeButton);

        // place the vehicle button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(vehicleButton, c);
        contentPane.add(vehicleButton);

        // place the customer button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(customerButton, c);
        contentPane.add(customerButton);

        // place the reservation button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(reservationButton, c);
        contentPane.add(reservationButton);

        // place the rental button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(rentalButton, c);
        contentPane.add(rentalButton);

        // place the return button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(returnButton, c);
        contentPane.add(returnButton);

        vehicleTypeButton.addActionListener(e -> this.delegate.showVehicleTypes());
        vehicleButton.addActionListener(e -> this.delegate.showVehicles());
        customerButton.addActionListener(e -> this.delegate.showCustomers());
        reservationButton.addActionListener(e -> this.delegate.showReservations());
        rentalButton.addActionListener(e -> this.delegate.showRentals());
        returnButton.addActionListener(e -> this.delegate.showReturns());

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
