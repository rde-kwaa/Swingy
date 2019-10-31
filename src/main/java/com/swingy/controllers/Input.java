package com.swingy.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.swingy.views.*;

public class Input {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String get() {
        String in = "";
        if (!GUI.state) {
            try {
                in = br.readLine();
            } catch (IOException e) {
                Notify.EReadLine();
                System.exit(1);
                return "";
            }
        } else {
            while (!GUI.returnPress) {
                if (GUI.tf.getText() != null)
                    in = GUI.tf.getText();
            }
            GUI.returnPress = false;
            GUI.tf.setText("");
        }
        in = in.toUpperCase();
        App.setInterface(in);
        App.exit(in);
        return in;
    }
}