package ui;

import model.RentalModel;

import javax.swing.*;
import java.awt.*;

public class ShowRentalsUI extends JFrame {

    private RentalModel[] models;

    public ShowRentalsUI(RentalModel[] models) {
        super("Rentals");
        this.models = models;
    }

    public void showFrame() {
        String[][] tableContent = new String[models.length][10];
        for (int i = 0; i < models.length; i++) {
            tableContent[i][0] = models[i].getRentId().strip();
            tableContent[i][1] = models[i].getVehicleId().strip();
            tableContent[i][2] = models[i].getPhone().strip();
            tableContent[i][3] = models[i].getFromDate().strip();
            tableContent[i][4] = models[i].getFromTime().strip();
            tableContent[i][5] = models[i].getToDate().strip();
            tableContent[i][6] = models[i].getToTime().strip();
            tableContent[i][7] = models[i].getOdometer().strip();
            tableContent[i][8] = models[i].getCardNo().strip();
            if (models[i].getConfirmation() == null)
                tableContent[i][9] = "";
            else
                tableContent[i][9] = models[i].getConfirmation().strip();
        }
        String[] names = {
                "Rent ID",
                "Vehicle ID",
                "Phone",
                "From Date",
                "From Time",
                "To Date",
                "To Time",
                "Odometer",
                "Card Number",
                "Confirmation"
        };
        JTable table = new JTable(tableContent, names);
        table.getColumnModel().getColumn(0).setPreferredWidth(160);
        table.getColumnModel().getColumn(8).setPreferredWidth(160);
        table.getColumnModel().getColumn(9).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(table) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1200, 600);
            }
        };

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // place the pane
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(scrollPane, c);
        contentPane.add(scrollPane);

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
