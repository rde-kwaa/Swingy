package com.swingy.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.swingy.controllers.App;
import com.swingy.controllers.Input;
import com.swingy.views.GUI;
import com.swingy.views.Notify;

public class Map {
    public static int size;
    private static final Set<String> CARDINALS = new HashSet<String>(
            Arrays.asList(new String[] { "N", "E", "S", "W" }));

    public static String[][] newMap(Hero hero) {
        size = (hero.level - 1) * 5 + 10 - (hero.level % 2);
        size = (size % 2 == 0) ? size + 1 : size;
        String[][] map = new String[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++)
                map[i][j] = "X  ";
        }
        return map;
    }

    public static void printMap(Hero hero) {
        String[][] map = newMap(hero);
        int mid = map.length / 2;
        int offsetX = hero.gps.x;
        int offsetY = hero.gps.y;
        if (mid == hero.gps.x || mid == -hero.gps.y) {
            App.victory();
        }
        System.out.println("");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (j == size - 1)
                    System.out.println(map[i][j]);
                else if (i == mid + offsetY && j == mid + offsetX) {
                    System.out.print("O  ");
                } else
                    System.out.print(map[i][j]);
            }
        }
        System.out.println("");
    }

    public static boolean moveHero(Hero hero) {
        Notify.DirectionChoose();
        String direction = Input.get();
        switch (direction) {
        case ("N"):
            hero.gps.y -= 1;
            return true;
        case ("E"):
            hero.gps.x += 1;
            return true;
        case ("S"):
            hero.gps.y += 1;
            return true;
        case ("W"):
            hero.gps.x -= 1;
            return true;
        default:
            Notify.Cardinality();
            return false;
        }
    }
}