package com.swingy.views;

import com.swingy.models.Enemy;
import com.swingy.models.Hero;

public class Notify {

    public static void PrintType(String out) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (GUI.state)
            GUI.print(out);
        else
            System.out.println(out);
    }

    public static void PrintPower(String out) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (GUI.state)
            GUI.printPower(out);
        else
            System.out.println(out);
    }

    /*
     ** Artifacts
     */
    public static void ArtifactDrop() {
        String out = "\nAn artifact has been dropped.";
        PrintType(out);
    }

    public static void ArtifactEquip(Integer diff) {
        String out = "\nWould you like to equip this artifact? (Yes / No)";
        PrintType(out);
    }

    public static int BadHelm() {
        String out = "\nThis helm is currently worse or on par with your one equipped";
        PrintType(out);
        return 0;
    }

    public static int BadArmour() {
        String out = "\nThis armour is currently worse or on par with your one equipped";
        PrintType(out);
        return 0;
    }

    public static int BadWeapon() {
        String out = "\nThis weapon is currently worse or on par with your one equipped";
        PrintType(out);
        return 0;
    }

    /*
     ** Battle
     */

    public static void PowerLevel(int vigor, int eVigor, Enemy enemy) {
        String out = "\n" + enemy.fighterType + " Power Level: " + eVigor + "\nHero Power Level: " + vigor;
        PrintPower(out);
    }

    /*
     ** Enemies
     */
    public static void EnemyAppear(Enemy enemy) {
        String out = "\nFrom the darkness, appears a " + enemy.fighterType + ".";
        PrintType(out);
    }

    public static void EnemyDefeated(Enemy enemy) {
        String out = "\nYou have defeated the " + enemy.fighterType + ".";
        PrintType(out);
    }

    public static void EnemyFailure(Enemy enemy) {
        String out = "\nYou have been eviscerated by the " + enemy.fighterType + ".\n\nGAME OVER";
        PrintType(out);
    }

    public static void EnemyRunTrue(Enemy enemy) {
        String out = "\nYou managed to escape the " + enemy.fighterType + ".";
        PrintType(out);
    }

    public static void EnemyRunFail(Enemy enemy) {
        String out = "\nThe " + enemy.fighterType + " is too quick, there is no escape. You must fight.";
        PrintType(out);
    }

    /*
     ** Errors
     */
    public static void EReadLine() {
        String out = "\n" + "Error: Reading input failed." + "\n";
        PrintType(out);
    }

    /*
     ** Hero
     */
    public static void HeroName() {
        String out = "\n" + "Please enter your Hero's name:" + "\n";
        PrintType(out);
    }

    public static void HeroClass() {
        String out = "\n" + "Please enter your Hero's class:" + "\n";
        out += "\n" + "          Paladin  Rogue    Wizard" + "\n" + "Hit Points  100     50       50" + "\n"
                + "Defence     50      50       100" + "\n" + "Attack      50      100      50\n";
        PrintType(out);
    }

    public static void GainedExp(int exp) {
        String out = "\n" + "Exp points: " + exp;
        PrintType(out);
    }

    public static void HeroStats(Hero hero) {
        String out = "\nName: " + hero.name + "\nLevel: " + hero.level + " Exp: " + hero.exp + "\nMaxHP: " + hero.maxHp
                + " HP: " + hero.hp + "\nAtt: " + hero.att + " Def: " + hero.def + "\n";
        if (hero.helm != null) {
            out += "Helm: " + hero.helm.buff + " ";
        }
        if (hero.armour != null) {
            out += "Armour: " + hero.armour.buff + " ";
        }
        if (hero.weapon != null) {
            out += "Weapon: " + hero.weapon.buff;
        }
        PrintPower(out);
    }

    public static void LevelUp(Hero hero) {
        String out = "You have leveled up to Level " + hero.level;
        PrintType(out);
    }

    /*
     ** Notifications
     */
    public static void GUIMap() {
        String out = "\n" + "Please refer to the minimap in the console" + "\n";
        PrintType(out);
    }

    public static void NewOrLoad() {
        String out = "\n" + "Would you like to CREATE or LOAD a hero" + "\n";
        PrintType(out);
    }

    public static void HeroNamePrompt() {
        String out = "\n" + "Prompt: Please use between 1 and 32 characters." + "\n";
        PrintType(out);
    }

    public static void HeroClassDetails() {
        String out = "\n" + "          Paladin  Rogue    Wizard" + "\n" + "Hit Points  100     50       50" + "\n"
                + "Defence     50      50       100" + "\n" + "Attack      50      100      50";
        PrintType(out);
    }

    public static void Cardinality() {
        String out = "\n" + "Prompt: Your cardinal points are 'N', 'E', 'S', 'W'." + "\n";
        PrintType(out);
    }

    public static void DirectionChoose() {
        String out = "\n" + "Please choose a direction to travel: 'N', 'E', 'S', 'W'." + "\n";
        PrintType(out);
    }

    public static void FightOrRun() {
        String out = "\n" + "Do you choose to FIGHT or RUN?" + "\n";
        PrintType(out);
    }

    public static void Victory() {
        String out = "\n" + "VICTORY" + "\n";
        PrintType(out);
    }
}