package vn.edu.hcmus.student.sv19127611.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Objects;

/**
 * vn.edu.hcmus.student.sv19127611.GUI
 * Created by fminhtu
 * Date 12/17/2021 - 7:28 PM
 * Description: ...
 */
public class ButtonEvents implements ActionListener {
    private static final Hashtable<String, String> history = new Hashtable<>();

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

    }

    private void game1() {
    }

    private void random() {
    }

    private void reset() {
    }

    private void delete() {
    }

    private void edit() {
    }

    private void add() {
    }

    private void history() {
    }


    private void find2() {
    }

    private void find1() {
        JFrame f = new JFrame();
        try {
            String filename = JOptionPane.showInputDialog(f, "Enter slang word: ");
            JOptionPane.showMessageDialog(f, "Export file success");


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Error","Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
}

