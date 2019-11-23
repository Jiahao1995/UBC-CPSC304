package ui;

import model.VehicleTypeModel;

import javax.swing.*;
import java.awt.*;

public class ShowVehicleTypesUI extends JFrame {

    private VehicleTypeModel[] models;

    public ShowVehicleTypesUI(VehicleTypeModel[] models) {
        super("Vehicles Types");
        this.models = models;
    }

    public void showFrame() {
        String[][] tableContent = new String[models.length][9];
        for (int i = 0; i < models.length; i++) {
            tableContent[i][0] = models[i].getType().strip();
            tableContent[i][1] = models[i].getFeature().strip();
            tableContent[i][2] = String.valueOf(models[i].getWeeklyRate()).strip();
            tableContent[i][3] = String.valueOf(models[i].getDailyRate()).strip();
            tableContent[i][4] = String.valueOf(models[i].getHourlyRate()).strip();
            tableContent[i][5] = String.valueOf(models[i].getWeeklyInsurance()).strip();
            tableContent[i][6] = String.valueOf(models[i].getDailyInsurance()).strip();
            tableContent[i][7] = String.valueOf(models[i].getHourlyInsurance()).strip();
            tableContent[i][8] = String.valueOf(models[i].getKilometerRate()).strip();
        }
        String[] names = {
                "Type",
                "Feature",
                "Weekly Rate",
                "Daily Rate",
                "Hourly Rate",
                "Weekly Insurance",
                "Daily Insurance",
                "Hourly Insurance",
                "Kilometer Rate"
        };
        JTable table = new JTable(tableContent, names);

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
