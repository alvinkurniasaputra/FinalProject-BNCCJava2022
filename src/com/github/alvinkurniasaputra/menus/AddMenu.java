package com.github.alvinkurniasaputra.menus;

import com.github.alvinkurniasaputra.models.ToDo;
import com.github.alvinkurniasaputra.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenu extends JFrame implements ActionListener {

    private final User currentUser;

//    ArrayList<ToDo> todolist = new ArrayList<>();

    public JTextField contentField, timeField;
    public JButton saveBtn, registerLabelBtn;

    public AddMenu(User currentUser) {
        this.currentUser = currentUser;

//        if (args.length > 0) {
//            todolist.addAll((ArrayList<ToDo>)args[0]);
//        }

        this.setTitle("Add To-Do");
        this.setSize(600, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.buildFrame();

        this.setVisible(true);
    }

    private JPanel createCenterPanel(int baseMargin) {
        JPanel centerPanel = new JPanel(new GridLayout(2, 0, -150, -10));

        JPanel contentPanel = new JPanel(new GridLayout(1, 2, -350, 20));
        JPanel timePanel = new JPanel(new GridLayout(1, 2, -200, 20));

        JLabel todoLabel = new JLabel("Content"),
                timeLabel = new JLabel("Expire Time");

        contentField = new JTextField();
        timeField = new JTextField();

        contentPanel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin*2,
                baseMargin,
                baseMargin,
                baseMargin
        ));

        timePanel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin,
                baseMargin*2,
                baseMargin*3,
                baseMargin*2
        ));

        contentPanel.add(todoLabel);
        contentPanel.add(contentField);

        timePanel.add(timeLabel);
        timePanel.add(timeField);

        centerPanel.add(contentPanel);
        centerPanel.add(timePanel);

        return centerPanel;
    }

    private JPanel createBottomPanel(int baseMargin) {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 1));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                baseMargin * 3,
                baseMargin * 2,
                baseMargin * 3));

        saveBtn = new JButton("SAVE");

        saveBtn.addActionListener(this);
        saveBtn.setPreferredSize(new Dimension(180, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveBtn);

        bottomPanel.add(buttonPanel);

        return bottomPanel;
    }

    private void buildFrame() {
        JLabel titleLabel = new JLabel("Add To-Do", JLabel.CENTER);
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
        if (e.getSource() == saveBtn) {
            String titleError = "Add To-Do Error";

            String content = contentField.getText();
            String time = timeField.getText();

            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Content cannot be empty",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (content.length() < 10 || content.length() > 100) {
                JOptionPane.showMessageDialog(
                        this,
                        "Username must be ≥ 10 and ≤ 100 characters",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (time.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Expire Time cannot be empty",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!time.matches("[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Use format yyyy-MM-dd HH:mm:ss",
                        titleError, JOptionPane.ERROR_MESSAGE);
                return;
            }

            ToDo todo = new ToDo(0, currentUser.getId(), content, time, 0);
            currentUser.addTodotemp(todo);

            this.dispose();
            new DashboardMenu(currentUser);
        }
    }
}
