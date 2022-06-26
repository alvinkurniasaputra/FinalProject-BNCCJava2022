package com.github.alvinkurniasaputra.menus;

import com.github.alvinkurniasaputra.Main;
import com.github.alvinkurniasaputra.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterMenu extends JFrame implements ActionListener {

    public JTextField usernameField;
    public JPasswordField passwordField, confirmPasswordField;
    public JButton registerBtn, loginLabelBtn;

    public RegisterMenu() {
        this.setTitle("Register Menu");
        this.setSize(600, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.buildFrame();

        this.setVisible(true);
    }

    private JPanel createCenterPanel(int baseMargin) {
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, -100, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin * 2,
                baseMargin * 3,
                baseMargin * 3,
                baseMargin * 3
        ));

        JLabel usernameLabel = new JLabel("Username"),
                passwordLabel = new JLabel("Password"),
                confirmPasswordLabel = new JLabel("Confirm Password");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);

        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        centerPanel.add(confirmPasswordLabel);
        centerPanel.add(confirmPasswordField);

        return centerPanel;
    }

    private JPanel createBottomPanel(int baseMargin) {
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                baseMargin * 3,
                baseMargin * 2,
                baseMargin * 3));

        registerBtn = new JButton("REGISTER");
        loginLabelBtn = new JButton("Already have an account? Click here to login");

        registerBtn.addActionListener(this);
        registerBtn.setPreferredSize(new Dimension(180, 40));

        loginLabelBtn.setContentAreaFilled(false);
        loginLabelBtn.setBorder(null);
        loginLabelBtn.setFocusable(false);
        loginLabelBtn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerBtn);

        bottomPanel.add(buttonPanel);
        bottomPanel.add(loginLabelBtn);

        return bottomPanel;
    }

    private void buildFrame() {
        JLabel titleLabel = new JLabel("USER PROFILES", JLabel.CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 24));

        int baseMargin = 40;
        titleLabel.setBorder(BorderFactory.createEmptyBorder(baseMargin * 3, 0, 0, 0));

        JPanel centerPanel = this.createCenterPanel(baseMargin);
        JPanel bottomPanel = this.createBottomPanel(baseMargin);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerBtn) {
            String titleError = "Register Error";

            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Username cannot be empty",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (username.length() < 3 || username.length() > 16) {
                JOptionPane.showMessageDialog(
                        this,
                        "Username must be ≥ 3 and ≤ 16 characters\n",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean foundSameUser = Main.USER_HANDLER.doesUserExists(username);
            if (foundSameUser) {
                JOptionPane.showMessageDialog(
                        this,
                        "User with this username or email is already registered",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Password cannot be empty",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (password.length() < 8 || password.length() > 40 || !password.matches(".*[a-zA-Z].*") || !password.matches(".*[0-9].*")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Password must be ≥ 8 and ≤ 40 characters with a combination of letters and numbers\n",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!confirmPassword.equals(password)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Confirm password must be the same as Password",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = new User(0,username, password);
            Main.USER_HANDLER.registerUser(user);

            this.dispose();
            new LoginMenu();
        } else if (e.getSource() == loginLabelBtn) {
            this.dispose();
            new LoginMenu();
        }
    }

}
