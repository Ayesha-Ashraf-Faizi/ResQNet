package resqnet.service;

import javax.sound.sampled.*;
import javax.swing.*;

public class AlertService {

    public static void playSiren() {

        try {

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(
                            AlertService.class.getResource("/resqnet/emergency_siren.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {

            System.out.println("Siren could not be played.");

        }

    }

    public static void showEmergencyAlert(String message) {

        JOptionPane.showMessageDialog(
                null,
                message,
                "🚨 Emergency Alert",
                JOptionPane.WARNING_MESSAGE
        );

    }

}
