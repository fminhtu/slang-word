package vn.edu.hcmus.student.sv19127611;

import vn.edu.hcmus.student.sv19127611.GUI.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) { }
        MainFrame frame = new MainFrame();

    }

}
