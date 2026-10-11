package resqnet.gui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import resqnet.model.SOSRequest;
import resqnet.model.Volunteer;
import resqnet.service.DataStore;
import resqnet.util.UITheme;

public class AdminDashboard extends JFrame {

    private JTable requestTable, volunteerTable;
    private DefaultTableModel requestModel, volunteerModel;
    private JLabel totalSOSLabel, totalVolunteerLabel;

    public AdminDashboard() {

        setTitle("ResQNet - Admin Dashboard");
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = UITheme.createPanel();
        mainPanel.setLayout(new BorderLayout(15,15));

        JLabel title = UITheme.createTitle("Admin Dashboard");

        JPanel topPanel = new JPanel(new GridLayout(1,2,15,15));
        topPanel.setBackground(UITheme.BACKGROUND);

        totalSOSLabel = new JLabel("Total SOS : 0");
        totalSOSLabel.setFont(UITheme.HEADING_FONT);

        totalVolunteerLabel = new JLabel("Total Volunteers : 0");
        totalVolunteerLabel.setFont(UITheme.HEADING_FONT);

        topPanel.add(totalSOSLabel);
        topPanel.add(totalVolunteerLabel);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(title, BorderLayout.NORTH);
        northPanel.add(topPanel, BorderLayout.SOUTH);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        requestModel = new DefaultTableModel(
                new String[]{"Request ID","Victim","Emergency","Location","Status"},0);

        requestTable = new JTable(requestModel);
        requestTable.setRowHeight(28);
        requestTable.getTableHeader().setFont(UITheme.HEADING_FONT);

        volunteerModel = new DefaultTableModel(
                new String[]{"Volunteer","Skill","Availability"},0);

        volunteerTable = new JTable(volunteerModel);
        volunteerTable.setRowHeight(28);
        volunteerTable.getTableHeader().setFont(UITheme.HEADING_FONT);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(requestTable),
                new JScrollPane(volunteerTable));

        splitPane.setDividerLocation(350);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton assignBtn = new JButton("Assign Volunteer");
        JButton resolveBtn = new JButton("Mark Resolved");
        JButton refreshBtn = new JButton("Refresh");
        JButton backBtn = new JButton("Back");

        UITheme.styleButton(assignBtn, UITheme.PRIMARY);
        UITheme.styleButton(resolveBtn, UITheme.SUCCESS);
        UITheme.styleButton(refreshBtn, UITheme.SECONDARY);
        UITheme.styleButton(backBtn, UITheme.DANGER);

        buttonPanel.add(assignBtn);
        buttonPanel.add(resolveBtn);
        buttonPanel.add(refreshBtn);
        buttonPanel.add(backBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        loadData();
        assignBtn.addActionListener(e -> assignVolunteer());
        resolveBtn.addActionListener(e -> markResolved());
        refreshBtn.addActionListener(e -> loadData());

        backBtn.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });
    }

    private void loadData() {

        requestModel.setRowCount(0);
        volunteerModel.setRowCount(0);

        ArrayList<SOSRequest> requests =
                DataStore.loadData("requests.dat");

        ArrayList<Volunteer> volunteers =
                DataStore.loadData("volunteers.dat");

        totalSOSLabel.setText("Total SOS : " + requests.size());
        totalVolunteerLabel.setText("Total Volunteers : " + volunteers.size());

        for (SOSRequest request : requests) {
            requestModel.addRow(new Object[]{
                
                request.getRequestId(),
                    request.getVictim().getName(),
                    request.getVictim().getEmergencyType(),
                    request.getVictim().getLocation(),
                    request.getStatus()
            });
        }

        for (Volunteer volunteer : volunteers) {
            volunteerModel.addRow(new Object[]{
                    volunteer.getName(),
                    volunteer.getSkill(),
                    volunteer.isAvailable() ? "Available" : "Busy"
            });
        }
    }
    private void assignVolunteer() {

        int requestRow = requestTable.getSelectedRow();
        int volunteerRow = volunteerTable.getSelectedRow();

        if (requestRow == -1 || volunteerRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select both a SOS request and a volunteer.");
            return;
        }

        ArrayList<SOSRequest> requests =
                DataStore.loadData("requests.dat");

        ArrayList<Volunteer> volunteers =
                DataStore.loadData("volunteers.dat");

        String volunteerName = volunteers.get(volunteerRow).getName();

        requests.get(requestRow)
                .setStatus("Assigned (" + volunteerName + ")");

        volunteers.get(volunteerRow).setAvailable(false);

        DataStore.saveData(requests, "requests.dat");
        DataStore.saveData(volunteers, "volunteers.dat");

        JOptionPane.showMessageDialog(this,
                "Volunteer assigned successfully!");

        loadData();
    }
    private void markResolved() {

        int row = requestTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a SOS request.");
            return;
        }

        ArrayList<SOSRequest> requests =
                DataStore.loadData("requests.dat");

        requests.get(row).setStatus("Resolved");

        DataStore.saveData(requests, "requests.dat");

        JOptionPane.showMessageDialog(this,
                "SOS Request marked as Resolved.");

        loadData();
    }
}