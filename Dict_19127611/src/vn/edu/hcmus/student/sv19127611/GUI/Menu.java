package vn.edu.hcmus.student.sv19127611.GUI;

import vn.edu.hcmus.student.sv19127611.Dictionary.Dict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * vn.edu.hcmus.student.sv19127611.GUI
 * Created by fminhtu
 * Date 12/16/2021 - 4:31 PM
 * Description: ...
 */
public class Menu extends JFrame {
    ArrayList<JButton> buttonList = new ArrayList<JButton>(10);
    ArrayList<JLabel> labelList = new ArrayList<JLabel>(10);

    Container container;

    private enum Actions {
        find1, find2, history, add, edit, delete, reset, random, game1, game2;
    }


    public Menu() {
        Dict.loadHt("slang", ".txt");
        setLabelList();
        setJButton();

        container = getContentPane();
        container.setLayout(null);

        setBounds();
        addComponents();
        addActionListener();
    }

    public void setLabelList(){
        String[] label = {
                "Find meaning with a slang words",
                "Find slang words with a meaning",
                "Show search history",
                "Add a slang word",
                "Edit a slang word",
                "Delete a slang word",
                "Reset slang list",
                "Slang word on this day",
                "Funny quiz: slang-meaning",
                "Funny quiz: meaning-slang"
        };

        for (int i = 0; i < label.length; i++) {
            labelList.add(new JLabel(label[i]));
            System.out.println(label[i]);
        }
    }

    public void setJButton() {
        int length = 10;
        for (int i = 0; i < length; i++) {
            buttonList.add(new JButton("Select"));
        }
    }

    public void setBounds() {
        int xText = 10, textWidth = 200;
        int xButton = 220, buttonWidth = 80;
        int height = 25;
        int y = 10;

        for (int i = 0; i < labelList.size(); i++) {
            labelList.get(i).setBounds(xText, y, textWidth, height);
            buttonList.get(i).setBounds(xButton, y, buttonWidth, height);
            y += 30;
        }


    }


    public void addComponents() {
        for (int i = 0; i < labelList.size(); i++) {
            container.add(labelList.get(i));
            container.add(buttonList.get(i));
        }



    }

    public void addActionListener() {
        ButtonEvents instance = new ButtonEvents();

        int i = 0;
        for (Actions action : Actions.values()) {
//            System.out.println(action);
            buttonList.get(i).setActionCommand(action.name());
            buttonList.get(i).addActionListener(instance);
            i += 1;
        }


    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            Dict.saveHt("slang", ".txt");
            System.exit(0);
        }
    }

    public static void CreateAndShowGUI() {
        Menu frame = new Menu();
        frame.setTitle("Dictionary");
        frame.setVisible(true);
        frame.setBounds(250, 250, 330, 350);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

    }
}
