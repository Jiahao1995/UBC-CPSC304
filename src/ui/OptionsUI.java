package ui;

import delegates.OptionsDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OptionsUI extends JFrame {

    private OptionsDelegate delegate;

    public OptionsUI() {
        super("Options");
    }

    public void showFrame(OptionsDelegate delegate) {
        this.delegate = delegate;

        JLabel customerLabel = new JLabel("Customer: ");
        JLabel clerkLabel = new JLabel("Clerk: ");

        JButton searchButton = new JButton("Search Vehicles");
        JButton reserveButton = new JButton("Reserve a Vehicle");

        JButton rentButton = new JButton("Rent a Vehicle");
        JButton rentalReportButton = new JButton("Daily Rental Report");
        JButton branchRentalReportButton = new JButton("Daily Rental Report for Branch");

        JButton returnButton = new JButton("Return a Vehicle");
        JButton returnReportButton = new JButton("Daily Return Report");
        JButton branchReturnReportButton = new JButton("Daily Return Report for Branch");

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        // place the customer label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(customerLabel, c);
        contentPane.add(customerLabel);

        // place the search button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(searchButton, c);
        contentPane.add(searchButton);

        // place the reserve button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(reserveButton, c);
        contentPane.add(reserveButton);

        // place the clerk label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(30, 0, 10, 0);
        gb.setConstraints(clerkLabel, c);
        contentPane.add(clerkLabel);

        // place the rent button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(rentButton, c);
        contentPane.add(rentButton);

        // place the rental report button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(rentalReportButton, c);
        contentPane.add(rentalReportButton);

        // place the branch rental report button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 20, 0);
        gb.setConstraints(branchRentalReportButton, c);
        contentPane.add(branchRentalReportButton);

        // place the return button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(returnButton, c);
        contentPane.add(returnButton);

        // place the return report button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(returnReportButton, c);
        contentPane.add(returnReportButton);

        // place the branch return report button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(branchReturnReportButton, c);
        contentPane.add(branchReturnReportButton);

        // register customer button with action event handler
        searchButton.addActionListener(e -> this.delegate.search());
        reserveButton.addActionListener(e -> this.delegate.reserve());
        rentButton.addActionListener(e -> this.delegate.rent());
        rentalReportButton.addActionListener(e -> this.delegate.rentalReport());
        branchRentalReportButton.addActionListener(e -> this.delegate.branchRentalReport());
        returnButton.addActionListener(e -> this.delegate.returnBack());
        returnReportButton.addActionListener(e -> this.delegate.returnReport());
        branchReturnReportButton.addActionListener(e -> this.delegate.branchReturnReport());

        // anonymous inner class for closing the window
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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
        OptionsUI optionsUI = new OptionsUI();
        optionsUI.showFrame(new OptionsDelegate() {
            @Override
            public void search() {

            }

            @Override
            public void reserve() {

            }

            @Override
            public void rent() {

            }

            @Override
            public void rentalReport() {

            }

            @Override
            public void branchRentalReport() {

            }

            @Override
            public void returnBack() {

            }

            @Override
            public void returnReport() {

            }

            @Override
            public void branchReturnReport() {

            }
        });
    }

}
