package ui;

import delegates.ReceiptDelegate;

import javax.swing.*;
import java.awt.*;

public class ReceiptUI extends JFrame {

    // delegate
    private ReceiptDelegate delegate;

    public ReceiptUI() {
        super("Receipt");
    }

    public void showFrame(ReceiptDelegate delegate) {
        this.delegate = delegate;

        JLabel approvedLabel = new JLabel("Reservation Approved!");
        approvedLabel.setFont(new Font("Black", Font.BOLD, 16));
        JLabel dateLabel = new JLabel("Date Issued: ");
        JLabel confirmationLabel = new JLabel("Confirmation Number: ");

        JLabel idLabel = new JLabel("ID: ");
        JLabel makeLabel = new JLabel("Make: ");
        JLabel modelLabel = new JLabel("Model: ");
        JLabel yearLabel = new JLabel("Year: ");
        JLabel colorLabel = new JLabel("Color: ");
        JLabel odometerLabel = new JLabel("Odometer: ");
        JLabel typeLabel = new JLabel("Type: ");
        JLabel locationLabel = new JLabel("Location: ");
        JLabel cityLabel = new JLabel("City: ");
        JLabel fromLabel = new JLabel("From: ");
        JLabel toLabel = new JLabel("To: ");

        JButton printButton = new JButton("Print");
        JButton backButton = new JButton("Back");

        JPanel contentPanel = new JPanel();
        this.setContentPane(contentPanel);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPanel.setLayout(gb);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // place the approved label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(approvedLabel, c);
        contentPanel.add(approvedLabel);

        // place the confirmation number label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(confirmationLabel, c);
        contentPanel.add(confirmationLabel);

        // place the date label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 20, 0);
        gb.setConstraints(dateLabel, c);
        contentPanel.add(dateLabel);

        // place the id label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(idLabel, c);
        contentPanel.add(idLabel);

        // place the make label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(makeLabel, c);
        contentPanel.add(makeLabel);

        // place the model label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(modelLabel, c);
        contentPanel.add(modelLabel);

        // place the year label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(yearLabel, c);
        contentPanel.add(yearLabel);

        // place the color label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(colorLabel, c);
        contentPanel.add(colorLabel);

        // place the odometer label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(odometerLabel, c);
        contentPanel.add(odometerLabel);

        // place the type label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(typeLabel, c);
        contentPanel.add(typeLabel);

        // place the location label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(locationLabel, c);
        contentPanel.add(locationLabel);

        // place the city label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(cityLabel, c);
        contentPanel.add(cityLabel);

        // place the from label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(fromLabel, c);
        contentPanel.add(fromLabel);

        // place the to label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(toLabel, c);
        contentPanel.add(toLabel);


        // place the back button
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(20, 100, 0, 0);
        gb.setConstraints(backButton, c);
        contentPanel.add(backButton);

        // place the back button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20, 20, 0, 100);
        gb.setConstraints(printButton, c);
        contentPanel.add(printButton);

        // register buttons with action event handlers
        backButton.addActionListener(e -> this.dispose());

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
        ReceiptUI receiptUI = new ReceiptUI();
        receiptUI.showFrame(new ReceiptDelegate() {
            @Override
            public void back() {

            }

            @Override
            public void print() {

            }
        });
    }
}
