package ui;

import delegates.IdentityDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IdentityUI extends JFrame {

    // delegate
    private IdentityDelegate delegate;

    public IdentityUI() {
        super("Choose Your Identity");
    }

    public void showFrame(IdentityDelegate delegate) {
        this.delegate = delegate;

        JButton customerButton = new JButton("Customer");
        JButton clerkButton = new JButton("Clerk");
        JButton exitButton = new JButton("Exit");

        JPanel contentPanel = new JPanel();
        this.setContentPane(contentPanel);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPanel.setLayout(gb);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // place the customer button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(customerButton, c);
        contentPanel.add(customerButton);

        // place the clerk button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(clerkButton, c);
        contentPanel.add(clerkButton);

        // place the exit button
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(20, 0, 0, 0);
        gb.setConstraints(exitButton, c);
        contentPanel.add(exitButton);

        // register buttons with action event handlers
        customerButton.addActionListener(e -> this.delegate.chooseCustomer());
        clerkButton.addActionListener(e -> this.delegate.chooseClerk());
        exitButton.addActionListener(e -> this.delegate.exit());

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
        IdentityUI identityUI = new IdentityUI();
        identityUI.showFrame(new IdentityDelegate() {
            @Override
            public void chooseCustomer() {

            }

            @Override
            public void chooseClerk() {

            }

            @Override
            public void exit() {

            }
        });
    }

}
