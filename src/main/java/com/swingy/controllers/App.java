package com.swingy.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.swingy.models.Artifact;
import com.swingy.models.Battle;
import com.swingy.models.FighterTypes;
import com.swingy.models.Hero;
import com.swingy.models.Map;
import com.swingy.views.GUI;
import com.swingy.views.Notify;

public class App {
    static Hero save;

    public static void main(String[] args) {
        if (args == null)
            setInterface(args[0]);

        Integer play = 1;
        Hero hero = start();
        save = hero;

        while (play != 0) {
            Notify.HeroStats(hero);
            Map.printMap(hero);
            if (Map.moveHero(hero)) {
                if (Battle.encounter()) {
                    if ((play = Battle.fight(hero)) == 1) {
                        Artifact arti = new Artifact(hero);
                        if (arti.buff != 0 && hero.pickUpArtifact(arti)) {
                            hero.equip(arti);
                        }
                    }
                }
            }
        }
    }

    public static Hero newHero() {
        String name = "";
        String type = "";

        Notify.HeroName();
        name = Input.get().trim();
        while (name.equals("") || name.length() > 32) {
            Notify.HeroNamePrompt();
            Notify.HeroName();
            name = Input.get().trim();
        }

        while (!(FighterTypes.contains(type))) {
            Notify.HeroClass();
            type = Input.get();
        }

        return new Hero(name, type, 1);
    }

    static void exit(String input) {
        if (input.equals("EXIT")) {
            if (save != null) {
                String string = save.name + " " + save.fighterType + " " + save.level + " " + save.exp;
                if (save.helm != null) {
                    string += " " + save.helm.type + " " + save.helm.buff;
                }
                if (save.armour != null) {
                    string += " " + save.armour.type + " " + save.armour.buff;
                }
                if (save.weapon != null) {
                    string += " " + save.weapon.type + " " + save.weapon.buff;
                }
                if (save.gps != null) {
                    string += " GPS: " + save.gps.x + " " + save.gps.y;
                }
                WriteToFile.writeTheThing(string);
                try {
                    WriteToFile.fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Cannot access output file");
                }
            }
            System.exit(1);
        }
    }

    public static Hero start() {
        String input = "";

        while (!(input.equals("CREATE") || input.equals("LOAD"))) {
            Notify.NewOrLoad();
            input = Input.get();
        }

        switch (input) {
        case "CREATE":
            return newHero();
        case "LOAD":
            return loadSave();
        default:
            return newHero();
        }
    }

    public static void victory() {
        Notify.Victory();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    static void setInterface(String args) {
        if (args.toUpperCase().equals("GUI")) {
            GUI.state = true;
            GUI.showtime(true);
        } else if (args.toUpperCase().equals("CONSOLE")) {
            GUI.state = false;
            GUI.showtime(false);
        }
    }

    private static Hero loadSave() {
        String name, heroType = "";
        int level, experience, helm = 0, armour = 0, weapon = 0, x = 0, y = 0;
        int i = 4;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
            String line = reader.readLine();
            if (line != null) {
                reader.close();
                name = line.split(" ")[0].toUpperCase();
                heroType = line.split(" ")[1].toUpperCase();
                level = Integer.parseInt(line.split(" ")[2]);
                experience = Integer.parseInt(line.split(" ")[3]);
                while (true) {
                    if (i + 1 > line.split(" ").length) {
                        break;
                    }
                    switch (line.split(" ")[i].toUpperCase()) {
                    case "HELM":
                        helm = Integer.parseInt(line.split(" ")[i + 1]);
                        break;
                    case "ARMOUR":
                        armour = Integer.parseInt(line.split(" ")[i + 1]);
                        break;
                    case "WEAPON":
                        weapon = Integer.parseInt(line.split(" ")[i + 1]);
                        break;
                    case "GPS:":
                        x = Integer.parseInt(line.split(" ")[i + 1]);
                        y = Integer.parseInt(line.split(" ")[i + 2]);
                        break;
                    }
                    i += 2;
                }
                return new Hero(name, heroType, level, experience, helm, armour, weapon, x, y);
            }
            reader.close();
            return newHero();
        } catch (FileNotFoundException e) {
            // Show.fileNotFoundException();
            return newHero();
        } catch (IOException e) {
            // Show.iOException();
            return newHero();
        } catch (ArrayIndexOutOfBoundsException e) {
            // Show.arrayIndexOutOfBoundsException();
            return newHero();
        }

    }
}
