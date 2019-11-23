package ui;

import model.VehicleModel;

import javax.swing.*;
import java.awt.*;

public class ShowVehiclesUI extends JFrame {

    private VehicleModel[] models;

    public ShowVehiclesUI(VehicleModel[] models) {
        super("Vehicles");
        this.models = models;
    }

    public void showFrame() {
        String[][] tableContent = new String[models.length][10];
        for (int i = 0; i < models.length; i++) {
            tableContent[i][0] = models[i].getId().strip();
            tableContent[i][1] = models[i].getLicense().strip();
            tableContent[i][2] = models[i].getMake().strip();
            tableContent[i][3] = models[i].getModel().strip();
            tableContent[i][4] = models[i].getYear().strip();
            tableContent[i][5] = models[i].getColor().strip();
            tableContent[i][6] = String.valueOf(models[i].getOdometer()).strip();
            tableContent[i][7] = models[i].getStatus().strip();
            tableContent[i][8] = models[i].getType().strip();
            tableContent[i][9] = models[i].getBranch().strip();
        }
        String[] names = {
                "Vehicle ID",
                "Vehicle License",
                "Make",
                "Model",
                "Year",
                "Color",
                "Odometer",
                "Status",
                "Type",
                "Branch"
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
