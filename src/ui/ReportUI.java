package ui;

import model.BranchModel;
import model.DailyModel;
import model.TypeModel;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class ReportUI extends JFrame {

    private String name;
    private DailyModel[] dailyModels;
    private TypeModel[] typeModels;
    private BranchModel[] branchModels;
    private int totalNum;
    private double totalRevenue;

    public ReportUI(String name, DailyModel[] dailyModels, TypeModel[] typeModels, BranchModel[] branchModels, int totalNum, double totalRevenue) {
        super("Daily " + name + " Report");
        this.name = name;
        this.dailyModels = dailyModels;
        this.typeModels = typeModels;
        this.branchModels = branchModels;
        this.totalNum = totalNum;
        this.totalRevenue = totalRevenue;
    }

    public void showFrame() {
        DecimalFormat df = new DecimalFormat("#.##");

        JLabel dailyLabel = new JLabel("Daily " + name + ": ");
        JLabel typeLabel = new JLabel("Grouped by Type: ");
        JLabel branchLabel = new JLabel("Grouped by Branch: ");
        String s1 = "Total Number: " + totalNum;
        JLabel totalNumLabel = new JLabel(s1);

        String[] dailyNames = { "Branch", "Vehicle Type", "Rent ID" };
        String[][] dailyContents = new String[dailyModels.length][3];
        for (int i = 0; i < dailyModels.length; i++) {
            dailyContents[i][0] = dailyModels[i].getBranch();
            dailyContents[i][1] = dailyModels[i].getType();
            dailyContents[i][2] = dailyModels[i].getId();
        }
        JTable dailyTable = new JTable(dailyContents, dailyNames);
        JScrollPane dailyPane = new JScrollPane(dailyTable) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(500, 300);
            }
        };

        String[] typeNames = { "Vehicle Type", "Number", "Revenue" };
        String[][] typeContents = new String[typeModels.length][3];
        for (int i = 0; i < typeModels.length; i++) {
            typeContents[i][0] = typeModels[i].getType();
            typeContents[i][1] = String.valueOf(typeModels[i].getNum());
            typeContents[i][2] = String.valueOf(df.format(typeModels[i].getPrice()));
        }
        JTable typeTable = new JTable(typeContents, typeNames);
        JScrollPane typePane = new JScrollPane(typeTable) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(500, 100);
            }
        };

        String[] branchNames = { "Branch", "Number", "Revenue" };
        String[][] branchContents = new String[branchModels.length][3];
        for (int i = 0; i < branchModels.length; i++) {
            branchContents[i][0] = branchModels[i].getBranch();
            branchContents[i][1] = String.valueOf(branchModels[i].getNum());
            branchContents[i][2] = String.valueOf(df.format(branchModels[i].getPrice()));
        }
        JTable branchTable = new JTable(branchContents, branchNames);
        JScrollPane branchPane = new JScrollPane(branchTable) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(500, 100);
            }
        };

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // place the daily label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(dailyLabel, c);
        contentPane.add(dailyLabel);

        // place the daily pane
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(dailyPane, c);
        contentPane.add(dailyPane);

        // place the type label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(typeLabel, c);
        contentPane.add(typeLabel);

        // place the type pane
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(typePane, c);
        contentPane.add(typePane);

        // place the branch label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(branchLabel, c);
        contentPane.add(branchLabel);

        // place the branch pane
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(branchPane, c);
        contentPane.add(branchPane);

        // place the total number
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(totalNumLabel, c);
        contentPane.add(totalNumLabel);

        if (name.equals("Return")) {
            String s2 = "Total Revenue: " + df.format(totalRevenue);
            JLabel totalRevenueLabel = new JLabel(s2);
            // place the total revenue
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.insets = new Insets(0, 0, 10, 0);
            gb.setConstraints(totalRevenueLabel, c);
            contentPane.add(totalRevenueLabel);
        }

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
