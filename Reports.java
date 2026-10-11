package resqnet.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import resqnet.model.SOSRequest;
import resqnet.model.Volunteer;
import resqnet.service.DataStore;
import resqnet.util.UITheme;

public class Reports extends JFrame {

    public Reports() {

        setTitle("ResQNet - Reports");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = UITheme.createPanel();
        panel.setLayout(new BorderLayout(15, 15));

        panel.add(
                UITheme.createTitle("System Report"),
                BorderLayout.NORTH
        );

        ArrayList<SOSRequest> requests =
                DataStore.loadData("requests.dat");

        ArrayList<Volunteer> volunteers =
                DataStore.loadData("volunteers.dat");
                int pending = 0, assigned = 0, resolved = 0;

        for (SOSRequest request : requests) {

            String status = request.getStatus();

            if (status.startsWith("Assigned"))
                assigned++;

            else if (status.equals("Resolved"))
                resolved++;

            else
                pending++;
        }

        JTextArea reportArea = new JTextArea(
                "========== ResQNet Report ==========\n\n" +
                "Total SOS Requests : " + requests.size() + "\n" +
                "Pending Requests   : " + pending + "\n" +
                "Assigned Requests  : " + assigned + "\n" +
                "Resolved Requests  : " + resolved + "\n\n" +
                "Total Volunteers   : " + volunteers.size()
        );

        reportArea.setEditable(false);
        reportArea.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        panel.add(
                new JScrollPane(reportArea),
                BorderLayout.CENTER
        );
        JButton backBtn = new JButton("Back");
        UITheme.styleButton(backBtn, UITheme.DANGER);

        backBtn.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });

        JPanel bottom = new JPanel();
        bottom.add(backBtn);

        panel.add(bottom, BorderLayout.SOUTH);

        add(panel);
    }
}