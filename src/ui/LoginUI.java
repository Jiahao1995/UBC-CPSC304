package ui;

import delegates.LoginDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginUI extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 10;

    // components of the login window
    private JTextField usernameField;
    private JPasswordField passwordField;

    // delegate
    private LoginDelegate delegate;

    public LoginUI() {
        super("Login");
    }

    public void showFrame(LoginDelegate delegate) {
        this.delegate = delegate;

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel signInLabel = new JLabel(" as a ");
        JLabel signUpLabel = new JLabel("Don't have an account?");

        usernameField = new JTextField(TEXT_FIELD_WIDTH);
        passwordField = new JPasswordField(TEXT_FIELD_WIDTH);
        passwordField.setEchoChar('*');

        JButton signInButton = new JButton("Sign in");
        JButton signUpButton = new JButton("Sign up");

        JPanel contentPanel = new JPanel();
        this.setContentPane(contentPanel);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPanel.setLayout(gb);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // place the username label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(usernameLabel, c);
        contentPanel.add(usernameLabel);

        // place the username field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(usernameField, c);
        contentPanel.add(usernameField);

        // place the password label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 10, 10, 0);
        gb.setConstraints(passwordLabel, c);
        contentPanel.add(passwordLabel);

        // place the password field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 10);
        gb.setConstraints(passwordField, c);
        contentPanel.add(passwordField);

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(5, 10, 15, 0);
        gb.setConstraints(signUpButton, c);
        contentPanel.add(signUpButton);

        // place the sign in button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 15, 10);
        gb.setConstraints(signInButton, c);
        contentPanel.add(signInButton);

        // register login button with action event handler
        signUpButton.addActionListener(this);
        signInButton.addActionListener(this);

        // anonymous inner class for closing the window
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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

    @Override
    public void actionPerformed(ActionEvent e) {
        delegate.login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
    }

    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.showFrame(new LoginDelegate() {
            @Override
            public void login(String username, String password) {

            }

            @Override
            public void signUp(String username, String password) {

            }
        });
    }

}
