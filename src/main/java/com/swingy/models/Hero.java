package com.swingy.models;

import com.swingy.controllers.Input;
import com.swingy.views.GUI;
import com.swingy.views.Notify;

/**
 * Hero
 */
public class Hero extends Fighter {
    public String name;
    public int exp;
    public int vigor;
    public Coordinates gps;

    public Artifact helm;
    public Artifact armour;
    public Artifact weapon;

    public Hero(String name, String type, int level) {
        super(type, level);
        this.name = name;
        this.exp = 0;
        this.gps = new Coordinates(0, 0);
    }

    public Hero(String name, String type, int level, int exp) {
        super(type, level);
        this.name = name;
        this.exp = exp;
    }

    public Hero(String name, String type, int level, int exp, int helm, int armour, int weapon, int x, int y) {
        super(type, level);
        this.name = name;
        this.exp = exp;
        this.gps = new Coordinates(x, y);
        if (helm > 0) {
            this.helm = new Artifact("HELM", helm);
            if (GUI.state)
                GUI.hl.setText(GUI.hl.getText() + " " + this.helm.buff);
        }
        if (armour > 0) {
            this.armour = new Artifact("ARMOUR", armour);
            if (GUI.state)
                GUI.al.setText(GUI.al.getText() + " " + this.armour.buff);
        }
        if (weapon > 0) {
            this.weapon = new Artifact("WEAPON", weapon);
            if (GUI.state)
                GUI.wl.setText(GUI.wl.getText() + " " + this.weapon.buff);
        }
    }

    public Boolean pickUpArtifact(Artifact arti) {
        int diff = 0;
        int buff = arti.buff;
        switch (arti.type) {
        case HELM:
            if (this.helm != null) {
                diff = (buff > this.helm.buff) ? buff - this.helm.buff : Notify.BadHelm();
                ;
            }
            break;
        case ARMOUR:
            if (this.armour != null) {
                diff = (buff > this.armour.buff) ? buff - this.armour.buff : Notify.BadArmour();
            }
            break;
        case WEAPON:
            if (this.weapon != null) {
                diff = (buff > this.weapon.buff) ? buff - this.weapon.buff : Notify.BadWeapon();
            }
            break;
        }
        if (diff > 0) {
            System.out.println(
                    "This " + arti.type + " is " + diff + " point(s) better than your current " + arti.type + ". ");
        }
        Notify.ArtifactEquip(diff);
        if (Input.get().equals("YES")) {
            return true;
        } else {
            return false;
        }
    }

    public void equip(Artifact arti) {
        switch (arti.type) {
        case HELM:
            helm = arti;
            if (GUI.state)
                GUI.hl.setText(GUI.hl.getText() + " " + helm.buff);
            break;
        case ARMOUR:
            armour = arti;
            if (GUI.state)
                GUI.al.setText(GUI.al.getText() + " " + armour.buff);
            break;
        case WEAPON:
            weapon = arti;
            if (GUI.state)
                GUI.wl.setText(GUI.wl.getText() + " " + weapon.buff);
            break;
        }
    }

    public int vigor(int eVigor, Enemy enemy) {
        int hbuff = 0;
        int abuff = 0;
        int wbuff = 0;

        if (this.helm != null) {
            hbuff = this.helm.buff;

        }
        if (this.armour != null) {
            abuff = this.armour.buff;
        }
        if (this.weapon != null) {
            wbuff = this.weapon.buff;
        }
        vigor = this.att + this.def + this.maxHp + hbuff + abuff + wbuff;
        Notify.PowerLevel(vigor, eVigor, enemy);
        return vigor;
    }
}