package resqnet.util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UITheme {

    public static final Color PRIMARY =
            new Color(25, 118, 210);

    public static final Color SECONDARY =
            new Color(33, 150, 243);

    public static final Color SUCCESS =
            new Color(46, 125, 50);

    public static final Color DANGER =
            new Color(198, 40, 40);

    public static final Color BACKGROUND =
            new Color(245, 247, 250);

    public static final Font TITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 28);

    public static final Font HEADING_FONT =
            new Font("Segoe UI", Font.BOLD, 18);

    public static final Font NORMAL_FONT =
            new Font("Segoe UI", Font.PLAIN, 15);

    public static final Font BUTTON_FONT =
            new Font("Segoe UI", Font.BOLD, 15);
            public static void styleButton(
            JButton button,
            Color color
    ) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(BUTTON_FONT);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }

    public static JPanel createPanel() {

        JPanel panel = new JPanel();

        panel.setBackground(BACKGROUND);

        panel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        return panel;
    }

    public static JLabel createTitle(String text) {

        JLabel label =
                new JLabel(text, JLabel.CENTER);

        label.setFont(TITLE_FONT);
        label.setForeground(PRIMARY);

        return label;
    }
}