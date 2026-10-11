package resqnet.gui;

import resqnet.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginFrame() {

        setTitle("ResQNet - Admin Login");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = UITheme.createPanel();
        panel.setLayout(new BorderLayout(15,15));

        JLabel title = UITheme.createTitle("Administrator Login");
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2,2,10,10));
        form.setBackground(UITheme.BACKGROUND);

        form.add(new JLabel("Username"));
        txtUsername = new JTextField();
        form.add(txtUsername);

        form.add(new JLabel("Password"));
        txtPassword = new JPasswordField();
        form.add(txtPassword);

        panel.add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setBackground(UITheme.BACKGROUND);

        JButton loginBtn = new JButton("Login");
        JButton backBtn = new JButton("Back");

        UITheme.styleButton(loginBtn, UITheme.SUCCESS);
        UITheme.styleButton(backBtn, UITheme.DANGER);

        buttons.add(loginBtn);
        buttons.add(backBtn);

        panel.add(buttons, BorderLayout.SOUTH);

        add(panel);

        loginBtn.addActionListener(e -> login());

        backBtn.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });
    }

    private void login() {

        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.equals("admin") && password.equals("1234")) {

            JOptionPane.showMessageDialog(this,
                    "Login Successful!");

            new AdminDashboard().setVisible(true);
            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid Username or Password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);

        }
    }
}


