package resqnet.gui;
import java.awt.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;
import resqnet.model.SOSRequest;
import resqnet.model.Victim;
import resqnet.service.DataStore;
import resqnet.util.UITheme;
public class SOSForm extends JFrame {
        private String name, phone, location;
        private int peopleAffected;
        private JComboBox<String> emergencyBox;
    private JTextArea descriptionArea;

    public SOSForm(String name, String phone, String location, int peopleAffected) {

        this.name = name;
        this.phone = phone;
        this.location = location;
        this.peopleAffected = peopleAffected;

        setTitle("ResQNet - Emergency SOS");
        setSize(650,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = UITheme.createPanel();
        panel.setLayout(new BorderLayout(20,20));

        panel.add(UITheme.createTitle("Emergency SOS Request"),
                BorderLayout.NORTH);
                JPanel form = new JPanel(new GridLayout(2,2,15,15));
        form.setBackground(UITheme.BACKGROUND);

        form.add(new JLabel("Emergency Type"));

        emergencyBox = new JComboBox<>(new String[]{
                "Medical Emergency",
                "Fire",
                "Flood",
                "Earthquake",
                "Road Accident",
                "Building Collapse",
                "Other"
        });

        form.add(emergencyBox);
        form.add(new JLabel("Description"));

        descriptionArea = new JTextArea(5,20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JPanel centerPanel = new JPanel(new BorderLayout(10,10));
        centerPanel.setBackground(UITheme.BACKGROUND);

        centerPanel.add(form, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        panel.add(centerPanel, BorderLayout.CENTER);

        JButton sendButton = new JButton("🚨 SEND SOS");
        UITheme.styleButton(sendButton, UITheme.DANGER);

        add(panel, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendSOS());
    }
    private void sendSOS() {

        if (descriptionArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the emergency description.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    getClass().getResource("/resqnet/emergency_siren.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Victim victim = new Victim(
                name,
                phone,
                location,
                emergencyBox.getSelectedItem().toString(),
                peopleAffected,
                descriptionArea.getText().trim()
        );

        ArrayList<SOSRequest> requests =
                DataStore.loadData("requests.dat");

        requests.add(new SOSRequest(victim));
        DataStore.saveData(requests, "requests.dat");

        JOptionPane.showMessageDialog(
                this,
                "🚨 SOS Request Submitted Successfully!\n\nEmergency services have been notified.",
                "ResQNet",
                JOptionPane.INFORMATION_MESSAGE);

        new HomeScreen().setVisible(true);
        dispose();
    }
}