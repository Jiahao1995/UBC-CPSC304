package ui;

import delegates.SignUpDelegate;

import javax.swing.*;
import java.awt.*;

public class SignUpUI extends JFrame {

    private SignUpDelegate delegate;

    public SignUpUI() {
        super("Sign Up");
    }

    public void showFrame(SignUpDelegate delegate) {
        this.delegate = delegate;

        JLabel phoneLabel = new JLabel("Phone: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel licenseLabel = new JLabel("License: ");
        JLabel addressLabel = new JLabel("Address: ");

        JTextField phoneField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField licenseField = new JTextField(20);
        JTextField addressField = new JTextField(20);

        JButton button = new JButton("Sign Up");

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // place the phone label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(phoneLabel, c);
        contentPane.add(phoneLabel);

        // place the phone field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(phoneField, c);
        contentPane.add(phoneField);

        // place the name label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(nameLabel, c);
        contentPane.add(nameLabel);

        // place the name field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(nameField, c);
        contentPane.add(nameField);

        // place the license label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(licenseLabel, c);
        contentPane.add(licenseLabel);

        // place the license field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(licenseField, c);
        contentPane.add(licenseField);

        // place the address label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(addressLabel, c);
        contentPane.add(addressLabel);

        // place the address field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(addressField, c);
        contentPane.add(addressField);

        // place the button
        c.gridwidth = GridBagConstraints.EAST;
        c.insets = new Insets(20, 0, 0, 0);
        gb.setConstraints(button, c);
        contentPane.add(button);

        // register
        button.addActionListener(e -> this.delegate.signUp(
                phoneField.getText(),
                nameField.getText(),
                licenseField.getText(),
                addressField.getText()
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
        SignUpUI signUpUI = new SignUpUI();
        signUpUI.showFrame(new SignUpDelegate() {
            @Override
            public void signUp(String phone, String name, String license, String address) {

            }
        });
    }

}
