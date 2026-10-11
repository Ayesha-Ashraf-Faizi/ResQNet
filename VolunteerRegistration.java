package resqnet.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import resqnet.model.Volunteer;
import resqnet.service.DataStore;
import resqnet.util.UITheme;

public class VolunteerRegistration extends JFrame {

    private JTextField txtName, txtPhone, txtLocation;
    private JComboBox<String> skillBox;

    public VolunteerRegistration() {

        setTitle("ResQNet - Volunteer Registration");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = UITheme.createPanel();
        panel.setLayout(new BorderLayout(20, 20));

        panel.add(
                UITheme.createTitle("Volunteer Registration"),
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
        form.add(new JLabel("Skill"));

        skillBox = new JComboBox<>(new String[]{
                "Medical Assistance",
                "First Aid",
                "Fire Rescue",
                "Water Rescue",
                "Food Distribution",
                "General Rescue"
        });

        form.add(skillBox);
        panel.add(form, BorderLayout.CENTER);

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");

        UITheme.styleButton(registerBtn, UITheme.SUCCESS);
        UITheme.styleButton(backBtn, UITheme.DANGER);

        JPanel buttons = new JPanel();
        buttons.setBackground(UITheme.BACKGROUND);

        buttons.add(registerBtn);
        buttons.add(backBtn);

        panel.add(buttons, BorderLayout.SOUTH);

        add(panel);

        registerBtn.addActionListener(
                e -> registerVolunteer()
        );

        backBtn.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });
    }
    private void registerVolunteer() {

        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String location = txtLocation.getText().trim();
        String skill = skillBox.getSelectedItem().toString();

        if (name.isEmpty() || phone.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields."
            );
            return;
        }

        ArrayList<Volunteer> volunteers =
                DataStore.loadData("volunteers.dat");

        volunteers.add(
                new Volunteer(name, phone, location, skill)
        );

        DataStore.saveData(
                volunteers,
                "volunteers.dat"
        );

        JOptionPane.showMessageDialog(
                this,
                "✅ Volunteer Registered Successfully!"
        );

        new HomeScreen().setVisible(true);
        dispose();
    }
}