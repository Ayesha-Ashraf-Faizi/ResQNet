package resqnet.gui;

import java.awt.*;
import javax.swing.*;
import resqnet.util.UITheme;

public class HomeScreen extends JFrame {

    public HomeScreen() {

        setTitle("ResQNet - Disaster & Emergency Response System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = UITheme.createPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));

        mainPanel.add(
                UITheme.createTitle(
                        "ResQNet - Disaster & Emergency Response System"
                ),
                BorderLayout.NORTH
        );

        JPanel centerPanel =
                new JPanel(new GridLayout(3, 2, 20, 20));

        centerPanel.setBackground(UITheme.BACKGROUND);

        JButton victimBtn =
                new JButton("👤 Victim Registration");

        JButton volunteerBtn =
                new JButton("🙋 Volunteer Registration");

        JButton adminBtn =
                new JButton("👨‍💼 Admin Dashboard");
                JButton resourceBtn =
                new JButton("🚑 Resource Tracker");

        JButton reportsBtn =
                new JButton("📊 Reports");

        JButton exitBtn =
                new JButton("❌ Exit");

        UITheme.styleButton(victimBtn, UITheme.PRIMARY);
        UITheme.styleButton(volunteerBtn, UITheme.SUCCESS);
        UITheme.styleButton(adminBtn, UITheme.SECONDARY);
        UITheme.styleButton(resourceBtn, UITheme.PRIMARY);
        UITheme.styleButton(reportsBtn, UITheme.SUCCESS);
        UITheme.styleButton(exitBtn, UITheme.DANGER);

        centerPanel.add(victimBtn);
        centerPanel.add(volunteerBtn);
        centerPanel.add(adminBtn);
        centerPanel.add(resourceBtn);
        centerPanel.add(reportsBtn);
        centerPanel.add(exitBtn);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        JLabel footer = new JLabel(
                "University Emergency Response Management System",
                JLabel.CENTER
        );

        footer.setFont(UITheme.NORMAL_FONT);

        
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        victimBtn.addActionListener(e -> {
            new VictimRegistration().setVisible(true);
            dispose();
        });

        volunteerBtn.addActionListener(e -> {
            new VolunteerRegistration().setVisible(true);
            dispose();
        });

        adminBtn.addActionListener(e -> {
            new AdminDashboard().setVisible(true);
            dispose();
        });
        resourceBtn.addActionListener(e -> {
            new ResourceTracker().setVisible(true);
            dispose();
        });

        reportsBtn.addActionListener(e -> {
            new Reports().setVisible(true);
            dispose();
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }
}