package ui;

import delegates.SignInDelegate;

import javax.swing.*;
import java.awt.*;

public class SignInUI extends JFrame {

    private SignInDelegate delegate;

    public SignInUI() {
        super("Sign In");
    }

    public void showFrame(SignInDelegate delegate) {
        this.delegate = delegate;

        JLabel label = new JLabel("Phone: ");
        JTextField textField = new JTextField(10);
        JButton button = new JButton("Sign In");

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // place the label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(label, c);
        contentPane.add(label);

        // place the text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 0);
        gb.setConstraints(textField, c);
        contentPane.add(textField);

        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(button, c);
        contentPane.add(button);

        // register
        button.addActionListener(e -> this.delegate.signIn(textField.getText()));

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
        SignInUI signInUI = new SignInUI();
        signInUI.showFrame(new SignInDelegate() {
            @Override
            public void signIn(String phone) {

            }
        });
    }

}
