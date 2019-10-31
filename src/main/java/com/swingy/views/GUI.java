package com.swingy.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.SpringLayout;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
    public static boolean state = false;

    static JFrame frame = new JFrame("Swingy");;
    public static JLabel hl;
    public static JLabel wl;
    public static JLabel al;
    static JLabel outputArea;
    static JLabel displayStats;
    public static JTextField tf = new JTextField(32);;
    public static boolean returnPress = false;
    static String outputString = "";

    static void print() {
        GUI.outputArea.setText(GUI.stringToHtml(outputString));
        outputString = "";
    }

    public static void print(String string) {
        GUI.outputArea.setText(GUI.stringToHtml(string));
    }

    public static void printPower(String string) {
        GUI.displayStats.setText(GUI.stringToHtml(string));
    }

    public static void showtime(boolean open) {
        if (open) {
            Action action = new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    returnPress = true;
                }
            };

            // Create and set up a frame window
            JFrame.setDefaultLookAndFeelDecorated(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(0, 0, 600, 500);
            frame.setMinimumSize(new Dimension(600, 500));

            // Define the panel to hold the components
            JPanel panel = new JPanel();
            SpringLayout layout = new SpringLayout();
            hl = new JLabel("Helm: ");
            wl = new JLabel("Weapon: ");
            al = new JLabel("Armour: ");

            outputArea = new JLabel();
            displayStats = new JLabel();

            JLabel label = new JLabel("Type here");
            tf.addActionListener(action);

            panel.setSize(600, 500);
            panel.setLayout(layout);

            panel.add(label);
            panel.add(tf);
            panel.add(hl);
            panel.add(wl);
            panel.add(al);
            panel.add(outputArea);
            panel.add(displayStats);

            // Put constraint on components
            // label
            layout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.SOUTH, label, -15, SpringLayout.SOUTH, panel);
            // tf
            layout.putConstraint(SpringLayout.WEST, tf, 5, SpringLayout.EAST, label);
            layout.putConstraint(SpringLayout.SOUTH, tf, -10, SpringLayout.SOUTH, panel);
            // hl
            layout.putConstraint(SpringLayout.NORTH, hl, 15, SpringLayout.NORTH, panel);
            layout.putConstraint(SpringLayout.WEST, hl, 10, SpringLayout.WEST, panel);
            // wl
            layout.putConstraint(SpringLayout.NORTH, wl, 15, SpringLayout.NORTH, panel);
            layout.putConstraint(SpringLayout.WEST, wl, 45, SpringLayout.EAST, hl);
            // al
            layout.putConstraint(SpringLayout.NORTH, al, 15, SpringLayout.NORTH, panel);
            layout.putConstraint(SpringLayout.WEST, al, 45, SpringLayout.EAST, wl);
            // outputArea
            layout.putConstraint(SpringLayout.NORTH, outputArea, 50, SpringLayout.VERTICAL_CENTER, panel);
            layout.putConstraint(SpringLayout.WEST, outputArea, 10, SpringLayout.WEST, panel);
            // displayStats
            layout.putConstraint(SpringLayout.SOUTH, displayStats, -150, SpringLayout.NORTH, outputArea);
            layout.putConstraint(SpringLayout.WEST, displayStats, 10, SpringLayout.WEST, panel);

            // Set the window to be visible as the default to be false
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);

            Notify.GUIMap();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            frame.setVisible(false);
            // frame.dispose();
        }
    }

    static String stringToHtml(String string) {
        return "<html><pre>" + string.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>")
                + "</pre></html>";
    }
}
