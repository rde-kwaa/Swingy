package com.swingy.models;

import com.swingy.controllers.Input;
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

    public Hero(String name, String type, int level, int exp, int helm, int armour, int weapon) {
        super(type, level);
        this.name = name;
        this.exp = exp;
        if (helm > 0) {
            this.helm = new Artifact("HELM", helm);
        }
        if (armour > 0) {
            this.armour = new Artifact("ARMOUR", armour);
        }
        if (weapon > 0) {
            this.weapon = new Artifact("WEAPON", weapon);
        }
    }

    public Boolean pickUpArtifact(Artifact arti) {
        int diff = 0;
        int buff = arti.buff;
        switch (arti.type) {
            case HELM:
            if (this.helm != null) {
                diff = (buff > this.helm.buff) ? buff - this.helm.buff : Notify.BadHelm();;
            }
            break;
            case ARMOUR:
            if (this.armour != null) {
                diff = (buff > this.armour.buff) ? buff-this.armour.buff : Notify.BadArmour();
            }
            break;
            case WEAPON:
            if (this.weapon != null) {
                diff = (buff > this.weapon.buff) ? buff-this.weapon.buff : Notify.BadWeapon();
            }
            break;
        }
        if (diff > 0) {
            System.out.println("This "+arti.type+" is " + diff + " point(s) better than your current "+arti.type+". ");
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
            break;
            case ARMOUR:
            armour = arti;
            break;
            case WEAPON:
            weapon = arti;
            break;
        }
    }

    public int vigor() {
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
        Notify.HeroPowerLevel(vigor);
        return vigor;
    }
}