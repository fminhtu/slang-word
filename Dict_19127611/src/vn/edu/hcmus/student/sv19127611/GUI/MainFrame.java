package vn.edu.hcmus.student.sv19127611.GUI;

import vn.edu.hcmus.student.sv19127611.Dictionary.Dict;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * vn.edu.hcmus.student.sv19127611.GUI
 * Created by fminhtu
 * Date 1/3/2022 - 4:02 PM
 * Description: ...
 */
public class MainFrame extends JFrame {
    private enum Actions {
        find1, find2, history, add, edit, delete, reset, random, game1, game2;
    }

    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JPanel dictPanel;
    private JPanel gamePanel;
    private JPanel searchPane;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton resetButton;
    private JLabel aboutLabel;
    private JScrollPane historyScrollPane;
    private JTextArea historyArea;
    private JPanel headerPane2;
    private JCheckBox CCheckBox;
    private JComboBox searchType;
    private JComboBox modeComboBox;
    private JPanel modePane;
    private JLabel todayLabel2;
    private JLabel todayContentLabel2;
    private JLabel modeLabel;
    private JPanel checkboxPane;
    private JPanel toolPane;
    private JCheckBox ACheckBox;
    private JCheckBox BCheckBox;
    private JCheckBox DCheckBox;
    private JButton submitButton;
    private JButton selectButton;
    private JScrollPane searchScrollPane;
    private JTextPane questionPane;
    private JPanel headerPane1;
    private JLabel todayLabel1;
    private JLabel todayContentLabel1;
    private JPanel demoPanel;
    private JTextArea textArea;
    int index;

    private void setDataForComponents() {
        String[] column = {"Slang word", "Meaning"};
        JTable table = new JTable(new String[0][], column);
        table.setEnabled(false);
        searchScrollPane.getViewport().add(table);

        todayContentLabel1.setText(ButtonEvents.today_slang());
        todayContentLabel2.setText(ButtonEvents.today_slang());
    }

    public MainFrame() {
        Dict.loadHt("slang", ".txt");
        setDataForComponents();
        setActionListenerForButton();


        setMainFrame();
    }

    public void setMainFrame() {
        setContentPane(contentPane);

        addWindowListener(new MyWindowListener());
        setBounds(300, 100, 900, 600);
        setVisible(true);
        setTitle("Slang dictionary");
    }

    public void setActionListenerForButton() {
        ButtonEvents instance = new ButtonEvents();
        addButton.setActionCommand(Actions.add.name());
        addButton.addActionListener(instance);

        editButton.setActionCommand(Actions.edit.name());
        editButton.addActionListener(instance);

        deleteButton.setActionCommand(Actions.delete.name());
        deleteButton.addActionListener(instance);

        resetButton.setActionCommand(Actions.reset.name());
        resetButton.addActionListener(instance);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = searchType.getSelectedItem().toString();
                String[] column = {"Slang word", "Meaning"};
                String keyword = searchTextField.getText();

                if (type == "Meaning") {
                    ArrayList<String> slangList = Dict.findSlangWithKeyword(keyword);
                    String[][] data = new String[slangList.size()][2];

                    for (int i = 0; i < slangList.size(); i++) {
                        data[i][0] = slangList.get(i);
                        data[i][1] = Dict.getMeaning(slangList.get(i));
                    }

                    searchScrollPane.getViewport().removeAll();
                    searchScrollPane.getViewport().add(new JTable(data, column));
                } else if (type == "Slang word") {
                    String meaning = Dict.getMeaning(keyword);
                    String[][] data = new String[1][2];

                    data[0][0] = keyword;
                    data[0][1] = meaning;

                    searchScrollPane.getViewport().removeAll();
                    JTable table = new JTable(data, column);
                    table.setEnabled(false);
                    searchScrollPane.getViewport().add(table);

                    String history = keyword + '\n' + historyArea.getText();
                    historyArea.setText(history);
                }

            }
        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeQuestion();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = -1;
                JFrame f = new JFrame();
                if (index == 0) {
                    if (ACheckBox.isSelected() && !BCheckBox.isSelected() &&  !CCheckBox.isSelected() && !DCheckBox.isSelected()) {
                        r = 0;
                    }
                } else if (index == 1){
                    if (!ACheckBox.isSelected() && BCheckBox.isSelected() &&  !CCheckBox.isSelected() && !DCheckBox.isSelected()) {
                        r = 1;
                    }
                } else if (index == 2){
                    if (!ACheckBox.isSelected() && !BCheckBox.isSelected() &&  CCheckBox.isSelected() && !DCheckBox.isSelected()) {
                        r = 2;
                    }
                } else if (index == 3){
                    if (!ACheckBox.isSelected() && !BCheckBox.isSelected() &&  !CCheckBox.isSelected() && DCheckBox.isSelected()) {
                        r = 3;
                    }
                }

                if (r != -1) {
                    JOptionPane.showMessageDialog(f, "Correct answer");
                    changeQuestion();
                } else {
                    JOptionPane.showMessageDialog(f, "Incorrect answer");
                }
                ACheckBox.setSelected(false);
                BCheckBox.setSelected(false);
                CCheckBox.setSelected(false);
                DCheckBox.setSelected(false);
            }
        });
    }

    private void changeQuestion() {
        String mode = modeComboBox.getSelectedItem().toString();

        if (ButtonEvents.random_slang() == null | Dict.getMeaning(ButtonEvents.random_slang()) == null ){
            String content = "Cannot create any question.";
            questionPane.setText(content);
            submitButton.setEnabled(false);
            return;
        }
        submitButton.setEnabled(true);
        if (mode == "Guess meaning") {
            String slang = ButtonEvents.random_slang();
            String answer[] = {"A", "B", "C", "D"};
            String question = "'" + slang + "'" + " is meaning? \n";

            index = ButtonEvents.random_num(0, 3);
            for (int i = 0; i < 4; i++) {
                if (i == index) {
                    question += answer[i] + ". " +  Dict.getMeaning(slang) + "\n";
                } else {
                    question += answer[i] + ". " +  Dict.getMeaning(ButtonEvents.random_slang()) + "\n";
                }
            }
            questionPane.setText(question);
        } else if (mode == "Guess slang word"){
            String meaning = Dict.getMeaning(ButtonEvents.random_slang());
            String answer[] = {"A", "B", "C", "D"};
            String question = "'" + meaning + "'" + " is slang word? \n";

            index = ButtonEvents.random_num(0, 3);
            for (int i = 0; i < 4; i++) {
                if (i == index) {
                    question += answer[i] + ". " +  Dict.getSlang(meaning) + "\n";
                } else {
                    question += answer[i] + ". " +  Dict.getMeaning(ButtonEvents.random_slang()) + "\n";
                }
            }
            questionPane.setText(question);
        }
    }

    static class MyWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            Dict.saveHt("slang", ".txt");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) { }
        MainFrame frame = new MainFrame();

    }
}
