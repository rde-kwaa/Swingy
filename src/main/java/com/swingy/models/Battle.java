package com.swingy.models;

import java.util.Random;

import com.swingy.controllers.Input;
import com.swingy.views.Notify;

public class Battle {
    public static boolean encounter() {
        Random r = new Random();
        if (r.nextDouble() < 0.5) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean run(Enemy enemy) {
        String in = "";
        Notify.EnemyAppear(enemy);
        while (!(in.equals("FIGHT") || in.equals("RUN"))) {
            Notify.FightOrRun();
            in = Input.get();
        }
        if (in.equals("FIGHT")) {
            return false;
        } else {
            Random r = new Random();
            if (r.nextBoolean()) {
                Notify.EnemyRunTrue(enemy);
                return true;
            } else {
                Notify.EnemyRunFail(enemy);
                return false;
            }
        }
    }

    public static void getExp(Hero hero, Enemy enemy, int vigor) {
        int exp = vigor * hero.level;
        hero.exp = hero.exp + exp;
        Notify.GainedExp(exp);
        levelUp(hero);
    }

    public static void levelUp(Hero hero) {
        int requiredExp = hero.level * 1000 + (hero.level - 1) * (hero.level - 1) * 450;
        if (hero.exp > requiredExp) {
            hero.level += 1;
            hero.att += (hero.level * 15);
            hero.def += (hero.level * 15);
            hero.maxHp += (hero.level * 15);
            hero.hp = hero.maxHp;
            hero.exp -= requiredExp;
            Notify.LevelUp(hero);
        }
    }

    public static int fight(Hero hero) {
        Enemy enemy = Enemy.getEnemy(hero);
        if (Battle.run(enemy)) {
            return 2;
        }
        int eVigor = enemy.vigor(hero);
        if (eVigor > hero.vigor(eVigor, enemy)) {
            Notify.EnemyFailure(enemy);
            return 0;
        } else {
            Notify.EnemyDefeated(enemy);
            getExp(hero, enemy, eVigor);
            levelUp(hero);
            return 1;
        }
    }
}