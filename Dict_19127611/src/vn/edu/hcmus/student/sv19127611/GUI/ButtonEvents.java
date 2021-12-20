package vn.edu.hcmus.student.sv19127611.GUI;

import vn.edu.hcmus.student.sv19127611.Dictionary.Dict;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * vn.edu.hcmus.student.sv19127611.GUI
 * Created by fminhtu
 * Date 12/17/2021 - 7:28 PM
 * Description: ...
 */
public class ButtonEvents implements ActionListener {
    ArrayList<String> history = new ArrayList<String>();

    private enum Actions {
        find1, find2, history, add, edit, delete, reset, random, game1, game2;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), Actions.find1.name())) {
            find1();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.find2.name())) {
            find2();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.history.name())) {
            history();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.add.name())) {
            add();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.add.name())) {
            add();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.edit.name())) {
            edit();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.delete.name())) {
            delete();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.reset.name())) {
            reset();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.random.name())) {
            random();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.game1.name())) {
            game1();
        }
        else if (Objects.equals(e.getActionCommand(), Actions.game2.name())) {
            game2();
        }

    }

    private void game2() {
        try {
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            String meaning = Dict.getMeaning(random_slang());
            JLabel question = new JLabel(meaning + " is slang word:");

            ArrayList<JCheckBox> checkbox = new ArrayList<JCheckBox>();
            for (int i = 0; i < 4; i++) {
                checkbox.add(new JCheckBox(random_slang()));
            }

            int index = random_num(0, 3);
            checkbox.get(index).setText(Dict.getSlang(meaning));

            panel.add(question);
            for (int i = 0; i < 4; i++) {
                panel.add(checkbox.get(i));
            }

            int selection = JOptionPane.showConfirmDialog(
                    f, panel, "Funny quiz meaning-slang", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (selection == JOptionPane.OK_OPTION) {
                if (correctAnswer(checkbox, index)) {
                    JOptionPane.showMessageDialog(f, "Correct answer");
                } else {
                    JOptionPane.showMessageDialog(f, "Incorrect answer");
                }
            }

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void game1() {
        try {
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            String slang = random_slang();
            JLabel question = new JLabel(slang + " is meaning:");

            ArrayList<JCheckBox> checkbox = new ArrayList<JCheckBox>();
            for (int i = 0; i < 4; i++) {
                checkbox.add(new JCheckBox(Dict.getMeaning(random_slang())));
            }

            int index = random_num(0, 3);
            checkbox.get(index).setText(Dict.getMeaning(slang));
            System.out.println(index);

            panel.add(question);
            for (int i = 0; i < 4; i++) {
                panel.add(checkbox.get(i));
            }

            int selection = JOptionPane.showConfirmDialog(
                    f, panel, "Funny quiz slang-meaning", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (selection == JOptionPane.OK_OPTION) {
                if (correctAnswer(checkbox, index)) {
                    JOptionPane.showMessageDialog(f, "Correct answer");
                } else {
                    JOptionPane.showMessageDialog(f, "Incorrect answer");
                }
            }

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean correctAnswer(ArrayList<JCheckBox> checkbox, int index) {
        for (int i = 0; i < 4; i++) {
            if (i == index) {
                continue;
            }

            if (checkbox.get(i).isSelected()) {
                return false;
            }
        }
        if (checkbox.get(index).isSelected()){
            return true;
        }
        return false;
    }

    private int random_num(int min, int max) {
        return (int)(Math.random() * (max - min + 1) + min);
    }

    private String random_slang() {
        Set<String> slangSet = Dict.getAllSlang();
        String slang = "default";
        int i = 0;
        int random_int = random_num(0, slangSet.size() - 1);

        for (String s : slangSet) {
            if (i == random_int) {
                slang = s;
                break;
            }
            i += 1;
        }

        return slang;
    }

    private void random() {
        JFrame f = new JFrame();

        try {
            Set<String> slangSet = Dict.getAllSlang();
            String slang = "default";
            int min = 0, max = slangSet.size() - 1, i = 0;

            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
            int random_int = dayOfYear % max;

            for (String s : slangSet) {
                if (i == random_int) {
                    slang = s;
                    break;
                }
                i += 1;
            }

            JOptionPane.showMessageDialog(f, "Slang word in this day: " + slang);

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reset() {
        JFrame f = new JFrame();

        try {
            Dict.clearAllData();
            Dict.saveHt("slang", ".txt");

            JOptionPane.showMessageDialog(f, "Reset success");

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void delete() {
        JFrame f = new JFrame();

        try {
            String slang = JOptionPane.showInputDialog(f, "Enter slang word: ");
            String meaning = Dict.getMeaning(slang);

            if (meaning == null) {
                JOptionPane.showMessageDialog(f, "The slang word does not existent");
            } else {
                JLabel contentLabel = new JLabel("Are you sure to delete this item?");

                int selection = JOptionPane.showOptionDialog(f, contentLabel,
                                                                "Selection",
                                                                JOptionPane.YES_NO_OPTION,
                                                                JOptionPane.WARNING_MESSAGE,
                                                                null,
                                                                null,
                                                                null);

                if (selection == JOptionPane.YES_OPTION) {
                    Dict.deleteSlang(slang);
                    Dict.deleteSlang(meaning);
                    JOptionPane.showMessageDialog(f, "Delete success");
                }
            }

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void edit() {
        JFrame f = new JFrame();

        try {
            String slang = JOptionPane.showInputDialog(f, "Enter slang word: ");
            String meaning = Dict.getMeaning(slang);

            if (meaning == null) {
                JOptionPane.showMessageDialog(f, "The slang word does not existent");
            } else {
                meaning = JOptionPane.showInputDialog(f, "Enter meaning: ", meaning);
                Dict.editSlang(slang, meaning);
                Dict.editMeaning(meaning, slang);

                JOptionPane.showMessageDialog(f, "Edit success");
            }

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private String duplicate(String slang, String meaning) {
        int i = 1;
        String new_slang = slang + " (1)";

        while (Dict.getMeaning(new_slang) != null) {
            i++;
            new_slang = slang + " (" + Integer.toString(i) + ")";
        }

        Dict.addSlang(new_slang, meaning);
        Dict.addMeaning(meaning, new_slang);

        return new_slang;
    }

    private void add() {
        JFrame f = new JFrame();

        try {
            String slang = JOptionPane.showInputDialog(f, "Enter slang word: ");

            if (Dict.getMeaning(slang) == null) {
                String meaning = JOptionPane.showInputDialog(f, "Enter meaning: ");

                Dict.addSlang(slang, meaning);
                Dict.addMeaning(meaning, slang);

                JOptionPane.showMessageDialog(f, "Add success");
            } else {
                JOptionPane.showMessageDialog(f, "The slang word does existent");

                Object[] options = { "Overwrite", "Duplicate", "Cancel" };
                JLabel contentLabel = new JLabel("Overwrite or Duplicate");

                int selection = JOptionPane.showOptionDialog(f,
                                                            contentLabel,
                                                            "Selection",
                                                            JOptionPane.YES_NO_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE,
                                                            null,
                                                            options,
                                                            null);

                if (selection == JOptionPane.YES_OPTION) {
                    String meaning = JOptionPane.showInputDialog(f, "Enter meaning: ");

                    Dict.editSlang(slang, meaning);
                    Dict.editMeaning(meaning, slang);

                    JOptionPane.showMessageDialog(f, "Overwrite success");

                } else if (selection == JOptionPane.NO_OPTION) {
                    String meaning = JOptionPane.showInputDialog(f, "Enter meaning: ");

                    String duplicate_slang = duplicate(slang, meaning);
                    JOptionPane.showMessageDialog(f, "Duplicate to " + duplicate_slang + " success");
                }

            }

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void history() {
        JFrame f = new JFrame();

        try {
            String[] column = {"Slang word", "Meaning"};
            String[][] data = new String[history.size()][2];

            for (int i = 0; i < history.size(); i++) {
                data[i][0] = history.get(i);
                data[i][1] = Dict.getMeaning(history.get(i));

            }

            JScrollPane scrollTable = new JScrollPane(new JTable(data, column));
            JOptionPane.showConfirmDialog(f, scrollTable, "Search history", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void find2() {
        JFrame f = new JFrame();

        try {
            String keyword = JOptionPane.showInputDialog(f, "Enter keyword: ");
            ArrayList<String> slangList = Dict.findSlangWithKeyword(keyword);
            String[][] data = new String[slangList.size()][2];
            String[] column = {"Slang word", "Meaning"};

            for (int i = 0; i < slangList.size(); i++) {
                data[i][0] = slangList.get(i);
                data[i][1] = Dict.getMeaning(slangList.get(i));
//                System.out.println(data[i][0] + data[i][1]);
            }

            JScrollPane scrollTable = new JScrollPane(new JTable(data, column));

            JOptionPane.showConfirmDialog(f, scrollTable, "Result", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }


    }

    private void find1() {
        JFrame f = new JFrame();

        try {
            String slang = JOptionPane.showInputDialog(f, "Enter slang word: ");
            String meaning = Dict.getMeaning(slang);
            history.add(slang);

            if (meaning == null) {
                JOptionPane.showMessageDialog(f, "The slang word is not found");
            } else {
                JOptionPane.showMessageDialog(f, slang + " = " + meaning);
            }

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }



}

