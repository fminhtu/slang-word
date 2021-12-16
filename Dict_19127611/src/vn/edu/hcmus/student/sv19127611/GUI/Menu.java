package vn.edu.hcmus.student.sv19127611.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * vn.edu.hcmus.student.sv19127611.GUI
 * Created by fminhtu
 * Date 12/16/2021 - 4:31 PM
 * Description: ...
 */
public class Menu extends JFrame implements ActionListener {
    JPanel history = new JPanel();
    JPanel selection = new JPanel();
    Container container;

    JTable jt;

    public Menu() {
        String[] column ={"ID","NAME","SALARY"};
        String[][] data ={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};

        jt = new JTable(data,column);
        history.add(jt);

        setBounds();
        addComponents();
        addActionListener();
    }

    public void addHistoryPane() {
    }


    public void setBounds() {
        int xText = 10, textWidth = 250, buttonWidth = 80;
        int xButton = 260;
        int height = 25;

        history.setBounds(50, 50, 250, 250);
    }


    public void addComponents() {
        add(history);
//        history.add(jt);
    }

    public void addActionListener() {


    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    public static void CreateAndShowGUI() {
        Menu frame = new Menu();
        frame.setTitle("Dictionary");
        frame.setVisible(true);
        frame.setBounds(250, 250, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }
}
