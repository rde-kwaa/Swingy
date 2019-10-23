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
import com.swingy.views.Notify;

public class App {
    static Hero save;
    public static void main(String[] args) {
        Integer play = 1;
        Hero hero = newHero();
        save = hero;

        while (play != 0) {
            Map.printMap(hero);
            if (Map.moveHero(hero)) {
                if (Battle.encounter()) {
                    if ((play = Battle.fight(hero)) == 1) {
                        Artifact arti = new Artifact(hero);
                        if (arti.buff != 0 && hero.pickUpArtifact(arti)) {
                            hero.equip(arti);
                            Notify.HeroStats(hero);
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
            Notify.HeroClassDetails();
            Notify.HeroClass();
            type = Input.get();
        }

        return new Hero(name, type, 1);
    }

    static void exit(String input) {
        if (input.equals("EXIT")) {
            if (save != null) {
                String string = save.name + " " + save.fighterType + " " + save.level + " "
                        + save.exp;
                if (save.helm != null) {
                    string += " " + save.helm.type + " " + save.helm.buff;
                }
                if (save.armour != null) {
                    string += " " + save.armour.type + " " + save.armour.buff;
                }
                if (save.weapon != null) {
                    string += " " + save.weapon.type + " " + save.weapon.buff;
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

    public static Hero setup() {
        String input = "";

        while (!(input.equals("CREATE") || input.equals("LOAD"))) {
            //Show.initialScreen();
            input = Input.get();
        }

        switch (input) {
        case "CREATE":
            return newHero();
        case "LOAD":
            return loadExistingHero();
        default:
            return newHero();
        }
    }

    private static Hero loadExistingHero() {
        String name, heroType = "";
        int level, experience, helm = 0, armour = 0, weapon = 0;
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
                    }
                    i += 2;
                }
                return new Hero(name, heroType, level, experience, helm, armour, weapon);
            }
            reader.close();
            return newHero();
        } catch (FileNotFoundException e) {
            //Show.fileNotFoundException();
            return newHero();
        } catch (IOException e) {
            //Show.iOException();
            return newHero();
        } catch (ArrayIndexOutOfBoundsException e) {
            //Show.arrayIndexOutOfBoundsException();
            return newHero();
        }

    }
}
