package com.swingy.models;

import java.util.Random;

import com.swingy.views.Notify;

/**
 * Enemy
 */
public class Enemy extends Fighter {

    public int vigor;

    Enemy(String type, Hero hero) {
        super(type, hero);
    }

    public static Enemy getEnemy(Hero hero) {
        Random r = new Random();
        Integer e = r.nextInt(3);
        switch(e) {
            case 0:
                return new Enemy("WRAITH", hero);
            case 1:
                return new Enemy("MINDFLAYER", hero);
            case 2:
                return new Enemy("TROLL", hero);
            default:
                return new Enemy("TROLL", hero);
        }
    }

    public static int enemyDifficulty(Hero hero) {
        Random r = new Random();
        Integer e = r.nextInt(3);
        switch(e) {
            case 0:
                return 4;
            case 1:
                return 5;
            case 2:
                return 7;
            default:
                return 1;
        }
    }

    public int vigor(Hero hero) {
        Random r = new Random();
        Integer e = r.nextInt(3);
        switch(e) {
            case 0:
                this.maxHp *= enemyDifficulty(hero);
                break;
            case 1:
                this.att *= enemyDifficulty(hero);
                break;
            case 2:
                this.def *= enemyDifficulty(hero);
                break;
            default:
                return 1;
        }
        vigor = this.maxHp + this.att + this.def;
        Notify.EnemyPowerLevel(vigor);
        return vigor;
    }
}