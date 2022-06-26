package com.github.alvinkurniasaputra.menus;

import com.github.alvinkurniasaputra.Main;
import com.github.alvinkurniasaputra.models.ToDo;
import com.github.alvinkurniasaputra.models.User;
import com.github.alvinkurniasaputra.panel.MyVerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class DashboardMenu extends JFrame implements ActionListener, ItemListener, MouseListener {

    private final User currentUser;

    ArrayList<ToDo> todo;
    public JButton addToDo, deleteToDo, refresh, viewHistory, saveChanges;
    public ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
    public ArrayList<JPanel> panelList = new ArrayList<>();

    Calendar cal = Calendar.getInstance();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String strDate = sdf.format(cal.getTime());
    LocalDate a1 = LocalDate.parse(strDate, dateFormatter);

    public DashboardMenu(User currentUser) {
        this.todo = Main.TODO_HANDLER.getToDo(currentUser);
        this.currentUser = currentUser;

        this.setTitle("Dashboard");
        this.setSize(600, 770);
        this.getContentPane().setBackground(Color.white);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.buildFrame();

        this.setVisible(true);
    }

    private JPanel createUpperPanel(int baseMargin) {
        JPanel upperPanel = new JPanel(new GridLayout(2,1));

        JLabel titleLabel = new JLabel("Hello, " + currentUser.getUsername());
        titleLabel.setFont(new Font(null, Font.PLAIN, 21));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin,
                baseMargin-5,
                baseMargin,
                0
        ));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.decode("#dfdce8"));

        JLabel subTitleLabel = new JLabel("To-Do List", JLabel.CENTER);
        subTitleLabel.setFont(new Font(null, Font.BOLD, 24));
        subTitleLabel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin,
                0,
                baseMargin,
                0
        ));
        subTitleLabel.setOpaque(true);
        subTitleLabel.setBackground(Color.white);

        upperPanel.add(titleLabel);
        upperPanel.add(subTitleLabel);

        upperPanel.setBorder(BorderFactory.createMatteBorder(
                baseMargin-10,
                baseMargin-10,
                0,
                baseMargin-10,
                Color.white
        ));

        return upperPanel;
    }

    private JPanel createContentPanel(int baseMargin) {
        JPanel wrapContentPanel = new JPanel(new GridLayout());
        JPanel contentPanel = new MyVerticalPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#dfdce8"));

        if (!currentUser.getEditTodoid().isEmpty()) {
            for (int i = 0; i < currentUser.getEditTodoid().size(); i++) {
                for (int j = 0; j < todo.size(); j++) {
                    if (todo.get(j).getId() == currentUser.getEditTodoid().get(i)) {
                        todo.get(j).setContent(currentUser.getEditTodocontent().get(i));
                        todo.get(j).setTime(currentUser.getEditTodotime().get(i));
                    }
                }
            }
        }

        if (!currentUser.getTodotemp().isEmpty()) {
            todo.addAll(currentUser.getTodotemp());
        }

        for (int i = todo.size() - 1; i >= 0; i--) {
            JPanel titlePanel = new JPanel();
            titlePanel.addMouseListener(this);
            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

            JLabel titleLabel = new JLabel("<HTML>" + todo.get(i).getContent() + "<br><br>" +
                    "<b>Expire time:</b> "+ todo.get(i).getTime() +"</HTML>");
            titleLabel.setFont(new Font(null, Font.PLAIN, 12));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(
                    baseMargin-10,
                    baseMargin-10,
                    baseMargin-10,
                    baseMargin-10
            ));

            JCheckBox checkBox = new JCheckBox();
            if (todo.get(i).isCompleted() == 1) {
                checkBox.setSelected(true);
            } else {
                checkBox.setSelected(false);
            }

            checkBox.setBorder(BorderFactory.createEmptyBorder(
                    0,
                    baseMargin-15,
                    0,
                    baseMargin-15
            ));
            checkBox.addItemListener(this);
            checkBoxList.add(checkBox);

            titlePanel.add(titleLabel);
            titlePanel.add(checkBox);
            if (LocalDate.parse(todo.get(i).getTime(), dateFormatter).isAfter(a1)) {
                titlePanel.setBackground(Color.red);
                checkBox.setBackground(Color.red);
            } else {
                titlePanel.setBackground(Color.white);
                checkBox.setBackground(Color.white);
            }
            titlePanel.setBorder(BorderFactory.createMatteBorder(
                    baseMargin-10,
                    baseMargin-10,
                    8,
                    baseMargin-10,
                    Color.decode("#dfdce8")
            ));
            panelList.add(titlePanel);
            contentPanel.add(titlePanel);
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getViewport().setBackground(Color.decode("#dfdce8"));
        scrollPane.setOpaque(true);
        wrapContentPanel.add(scrollPane);
        wrapContentPanel.setBorder(BorderFactory.createMatteBorder(
                0,
                baseMargin-10,
                baseMargin-10,
                baseMargin-10,
                Color.white
        ));

        return wrapContentPanel;
    }

    private JPanel createBottomPanel(int baseMargin) {
        JPanel wrapBottomPanel = new JPanel(new GridLayout(2,1,0,baseMargin-16));
        wrapBottomPanel.setBorder(BorderFactory.createMatteBorder(
                baseMargin-16,
                baseMargin-16,
                baseMargin-16,
                baseMargin-16,
                Color.white
        ));
        JPanel bottomPanel1 = new JPanel(new GridLayout(1,3,baseMargin-16,0));
        JPanel bottomPanel2 = new JPanel(new GridLayout(1,2,baseMargin-16,0));

        addToDo = new JButton("Add To-Do");
        addToDo.addActionListener(this);
        deleteToDo = new JButton("Delete To-Do");
        deleteToDo.addActionListener(this);
        refresh = new JButton("Refresh");
        refresh.addActionListener(this);

        bottomPanel1.add(addToDo);
        bottomPanel1.add(deleteToDo);
        bottomPanel1.add(refresh);

        viewHistory = new JButton("View History");
        saveChanges = new JButton("Save changes");

        viewHistory.addActionListener(this);
        saveChanges.addActionListener(this);

        bottomPanel2.add(viewHistory);
        bottomPanel2.add(saveChanges);

        wrapBottomPanel.add(bottomPanel1);
        wrapBottomPanel.add(bottomPanel2);

        return wrapBottomPanel;

    }

    private void buildFrame() {
        this.setLayout(new BorderLayout());

        int baseMargin = 25;

        JPanel upperPanel = this.createUpperPanel(baseMargin);
        JPanel contentPanel = this.createContentPanel(baseMargin);
        JPanel bottomPanel = this.createBottomPanel(baseMargin);

        this.add(upperPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToDo) {
            this.dispose();
            new AddMenu(currentUser);
        }

        if (e.getSource() == deleteToDo) {
            int reply = JOptionPane.showConfirmDialog(
                    this,
                    "This will delete permanently. Are you sure ?",
                    "Delete error", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                boolean isCheck = false;
                int temp = currentUser.getTodotemp().size();
                for (int i = 0; i < checkBoxList.size(); i++) {
                    if (checkBoxList.get(i).isSelected()) {
                        isCheck = true;
                        if (i < temp) {
                            currentUser.removeTodotemp((temp - 1) - i);
                        } else {
                            for (int j = 0; j < currentUser.getEditTodoid().size(); j++) {
                                if (todo.get(checkBoxList.size() - i - 1).getId() == currentUser.getEditTodoid().get(j)) {
                                    currentUser.removeEditTodoid(j);
                                    currentUser.removeEditTodocontent(j);
                                    currentUser.removeEditTodotime(j);
                                    break;
                                }
                            }
                            Main.TODO_HANDLER.deleteToDo(todo.get(checkBoxList.size() - i - 1));
                        }
                    }
                }
                this.dispose();
                new DashboardMenu(currentUser);
                if (isCheck) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Successfully deleted",
                            "Delete", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(
                        this,
                        "None are checked",
                        "Delete error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == viewHistory) {
            this.dispose();
            new HistoryMenu(currentUser);
        }

        if (e.getSource() == saveChanges) {
            for (ToDo todo : currentUser.getTodotemp()) {
                Main.TODO_HANDLER.addToDo(todo, currentUser);
            }
            currentUser.deleteTodotemp();
            JOptionPane.showMessageDialog(
                    this,
                    "Successfully saved",
                    "Save Changes", JOptionPane.INFORMATION_MESSAGE);

            for (int i = 0; i < currentUser.getEditTodoid().size(); i++) {
                Main.TODO_HANDLER.editContent(currentUser.getEditTodocontent().get(i), currentUser.getEditTodoid().get(i));
                Main.TODO_HANDLER.editTime(currentUser.getEditTodotime().get(i), currentUser.getEditTodoid().get(i));
            }
            todo = Main.TODO_HANDLER.getToDo(currentUser);

        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        for (int i = 0; i < checkBoxList.size(); i++) {
            if (e.getSource() == checkBoxList.get(i)) {
                if (checkBoxList.get(i).isSelected()) {
                    todo.get(checkBoxList.size() - i - 1).setCompleted(1);
                } else {
                    todo.get(checkBoxList.size() - i - 1).setCompleted(0);
                }
                Main.TODO_HANDLER.editIsCompleted(todo.get(checkBoxList.size() - i - 1));
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < panelList.size(); i++) {
            if (e.getSource() == panelList.get(i)) {
                this.dispose();
                new EditMenu(todo.get(panelList.size() - i - 1), currentUser, i);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
