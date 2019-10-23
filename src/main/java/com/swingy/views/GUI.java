package com.swingy.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
    public static boolean state = false;

    static JFrame frame;
    static JLabel outputArea;
    public static JTextField tf;
    public static boolean returnPress = false;
    static String outputString = "";
    
    static void print() {
        GUI.outputArea.setText(GUI.stringToHtml(outputString));
        outputString = "";
    }
    
    static void print(String string) {
        GUI.outputArea.setText(GUI.stringToHtml(string));
    }
    
    public static void showtime() {

        frame = new JFrame("Swingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);

        Action action = new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                returnPress = true;
            }
        };

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Type here");
        tf = new JTextField(30);
        tf.addActionListener(action);
        panel.add(label);
        panel.add(tf);

        outputArea = new JLabel();

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, outputArea);
        frame.setVisible(true);
    }

    static String stringToHtml(String string) {
        return "<html><pre>" + string.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</pre></html>";
    }
}
