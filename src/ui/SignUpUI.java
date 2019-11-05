package ui;

import delegates.SignUpDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SignUpUI extends JFrame {
    private static final int TEXT_FIELD_WIDTH = 10;

    // components of the sign up window
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    // delegate
    private SignUpDelegate delegate;

    public SignUpUI() {
        super("Sign Up");
    }

    public void showFrame(SignUpDelegate delegate) {
        this.delegate = delegate;

        JLabel usernameLabel = new JLabel("Set Username: ");
        JLabel passwordLabel = new JLabel("Set Password: ");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");

        usernameField = new JTextField(TEXT_FIELD_WIDTH);
        passwordField = new JPasswordField(TEXT_FIELD_WIDTH);
        passwordField.setEchoChar('*');
        confirmPasswordField = new JPasswordField(TEXT_FIELD_WIDTH);
        confirmPasswordField.setEchoChar('*');

        JButton signUpButton = new JButton("Sign Up");
        JButton cancelButton = new JButton("Cancel");

        JPanel contentPanel = new JPanel();
        this.setContentPane(contentPanel);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPanel.setLayout(gb);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // place the username label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 10, 5, 0);
        gb.setConstraints(usernameLabel, c);
        contentPanel.add(usernameLabel);

        // place the username field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 5, 10);
        gb.setConstraints(usernameField, c);
        contentPanel.add(usernameField);

        // place the password label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 10, 5, 0);
        gb.setConstraints(passwordLabel, c);
        contentPanel.add(passwordLabel);

        // place the password field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 5, 10);
        gb.setConstraints(passwordField, c);
        contentPanel.add(passwordField);

        // place the confirm password label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 10, 10, 0);
        gb.setConstraints(confirmPasswordLabel, c);
        contentPanel.add(confirmPasswordLabel);

        // place the confirm password field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 10);
        gb.setConstraints(confirmPasswordField, c);
        contentPanel.add(confirmPasswordField);

        // place the sign up button
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 10, 15, 0);
        gb.setConstraints(cancelButton, c);
        contentPanel.add(cancelButton);

        // place the sign in button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 15, 10);
        gb.setConstraints(signUpButton, c);
        contentPanel.add(signUpButton);
        
        // register buttons with action event handlers
        signUpButton.addActionListener(e -> this.delegate.signUp(
                usernameField.getText(),
                String.valueOf(passwordField.getPassword()),
                String.valueOf(confirmPasswordField.getPassword())
        ));
        cancelButton.addActionListener(e -> this.delegate.cancel());

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

        // place the cursor in the text field for the username
        usernameField.requestFocus();
    }

    public static void main(String[] args) {
        SignUpUI signUpUI = new SignUpUI();
        signUpUI.showFrame(new SignUpDelegate() {
            @Override
            public void signUp(String username, String password, String confirmPassword) {

            }
            @Override
            public void cancel() {

            }
        });
    }
}
