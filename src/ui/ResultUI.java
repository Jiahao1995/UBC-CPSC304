package ui;

import delegates.ResultDelegate;
import model.VehicleModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultUI extends JFrame {

    private ResultDelegate delegate;

    private VehicleModel[] result;

    public ResultUI(VehicleModel[] result) {
        super("Result");
        this.result = result;
    }

    public void showFrame(ResultDelegate delegate) {
        this.delegate = delegate;

        JLabel label = new JLabel("There are " + result.length + " vehicles based on your needs.");
        String[][] tableContent = new String[result.length][8];
        for (int i = 0; i < result.length; i++) {
            tableContent[i][0] = result[i].getId();
            tableContent[i][1] = result[i].getType();
            tableContent[i][2] = result[i].getMake();
            tableContent[i][3] = result[i].getModel();
            tableContent[i][4] = result[i].getYear();
            tableContent[i][5] = result[i].getColor();
            tableContent[i][6] = String.valueOf(result[i].getOdometer());
            tableContent[i][7] = result[i].getBranch();
        }
        String[] name = {
                "Vehicle Id",
                "Vehicle Type",
                "Make",
                "Model",
                "Year",
                "Color",
                "Odometer",
                "Branch"
        };
        JTable table = new JTable(tableContent, name);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // place the label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(label, c);
        contentPane.add(label);

        // place the table
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
