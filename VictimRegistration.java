package resqnet.gui;

import javax.swing.*;
import java.awt.*;
import resqnet.util.UITheme;

public class VictimRegistration extends JFrame {

    private JTextField txtName, txtPhone, txtLocation;
    private JSpinner peopleSpinner;

    public VictimRegistration() {

        setTitle("ResQNet - Victim Registration");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = UITheme.createPanel();
        panel.setLayout(new BorderLayout(20, 20));

        panel.add(
                UITheme.createTitle("Victim Registration"),
                BorderLayout.NORTH
        );

        JPanel form = new JPanel(new GridLayout(4, 2, 15, 15));
        form.setBackground(UITheme.BACKGROUND);

        form.add(new JLabel("Full Name"));
        form.add(txtName = new JTextField());

        form.add(new JLabel("Phone Number"));
        form.add(txtPhone = new JTextField());

        form.add(new JLabel("Current Location"));
        form.add(txtLocation = new JTextField());
        form.add(new JLabel("People Affected"));

        peopleSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 1000, 1)
        );

        form.add(peopleSpinner);

        panel.add(form, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Next");
        JButton backBtn = new JButton("Back");

        UITheme.styleButton(nextBtn, UITheme.SUCCESS);
        UITheme.styleButton(backBtn, UITheme.DANGER);

        JPanel buttons = new JPanel();
        buttons.setBackground(UITheme.BACKGROUND);

        buttons.add(nextBtn);
        buttons.add(backBtn);

        panel.add(buttons, BorderLayout.SOUTH);

        add(panel);
        nextBtn.addActionListener(e -> {

            String name = txtName.getText().trim();
            String phone = txtPhone.getText().trim();
            String location = txtLocation.getText().trim();
            int people = (Integer) peopleSpinner.getValue();

            if (name.isEmpty() || phone.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            new SOSForm(name, phone, location, people)
                    .setVisible(true);

            dispose();
        });

        backBtn.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });
    }
}