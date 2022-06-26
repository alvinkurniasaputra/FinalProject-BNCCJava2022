package com.github.alvinkurniasaputra.menus;

import com.github.alvinkurniasaputra.Main;
import com.github.alvinkurniasaputra.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JFrame implements ActionListener {

    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginBtn, registerLabelBtn;

    public LoginMenu() {
        this.setTitle("Login Menu");
        this.setSize(600, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.buildFrame();

        this.setVisible(true);
    }

    private JPanel createCenterPanel(int baseMargin) {
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, -200, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin * 4,
                baseMargin * 3,
                baseMargin * 4,
                baseMargin * 3
        ));

        JLabel usernameLabel = new JLabel("Username"),
                passwordLabel = new JLabel("Password");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);

        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        return centerPanel;
    }

    private JPanel createBottomPanel(int baseMargin) {
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                baseMargin * 3,
                baseMargin * 2,
                baseMargin * 3));

        loginBtn = new JButton("LOGIN");
        registerLabelBtn = new JButton("You don't have an account? Click here to register");

        loginBtn.addActionListener(this);
        loginBtn.setPreferredSize(new Dimension(180, 40));

        registerLabelBtn.setContentAreaFilled(false);
        registerLabelBtn.setBorder(null);
        registerLabelBtn.setFocusable(false);
        registerLabelBtn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginBtn);

        bottomPanel.add(buttonPanel);
        bottomPanel.add(registerLabelBtn);

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
        if (e.getSource() == loginBtn) {
            String titleError = "Login Error";

            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Username cannot be empty",
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

            User foundUser = Main.USER_HANDLER.findUser(username);
            if (foundUser == null || !foundUser.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Incorrect username or password",
                        titleError, JOptionPane.ERROR_MESSAGE);

                return;
            }

            this.dispose();
            new DashboardMenu(foundUser);
        } else if (e.getSource() == registerLabelBtn) {
            this.dispose();
            new RegisterMenu();
        }
    }

}
