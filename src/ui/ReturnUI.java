package ui;

import delegates.ReturnDelegate;

import javax.swing.*;
import java.awt.*;


public class ReturnUI extends JFrame {
    private static final int TEXT_FIELD_WIDTH = 20;

    private ReturnDelegate delegate;

    private JTextField idField;
    private JTextField odometerField;
    private JTextField fullField;

    public ReturnUI() {
        super("Return");
    }

    public void showFrame(ReturnDelegate delegate) {
        this.delegate = delegate;

        JLabel idLabel = new JLabel("Rent ID: ");
        JLabel odometerLabel = new JLabel("Odometer: ");
        JLabel fullLabel = new JLabel("Is Full Tank: ");

        idField = new JTextField(TEXT_FIELD_WIDTH);
        odometerField = new JTextField(TEXT_FIELD_WIDTH);
        fullField = new JTextField(TEXT_FIELD_WIDTH);

        JButton button = new JButton("Return");

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        // place the id label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(idLabel, c);
        contentPane.add(idLabel);

        // place the id field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(idField, c);
        contentPane.add(idField);

        // place the odometer label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(odometerLabel, c);
        contentPane.add(odometerLabel);

        // place the odometer field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(odometerField, c);
        contentPane.add(odometerField);

        // place the odometer label
        c.gridwidth = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 10);
        gb.setConstraints(fullLabel, c);
        contentPane.add(fullLabel);

        // place the odometer field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        gb.setConstraints(fullField, c);
        contentPane.add(fullField);

        // place the button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20, 0, 0, 0);
        gb.setConstraints(button, c);
        contentPane.add(button);

        // register the button
        button.addActionListener(e -> this.delegate.returnBack(
                idField.getText(),
                odometerField.getText(),
                fullField.getText()
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

}
