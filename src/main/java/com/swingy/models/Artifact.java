package com.swingy.models;

import java.util.Random;

import com.swingy.views.Notify;

public class Artifact {
    public ArtifactTypes type;
    public int buff = 0;

    public Artifact(Hero hero) {
        Random r = new Random();
        Integer p = r.nextInt(3);
        if (p % 2 == 0) {
            this.type = getArtiType();
            this.buff = getArtiBuff(hero);
        }
        if (this.buff != 0) {
            Notify.ArtifactDrop();
            System.out.println("Artifact Dropped: "+ this.type +"\nBuff: "+ this.buff);
        }
    }

    public Artifact(String type, int buff) {
        switch (type) {
            case "HELM":
            break;
            case "ARMOUR":
            break;
            case "WEAPON":
            break;
        }
        this.buff = buff;
    }

    int getArtiBuff(Hero hero) { 
        Random r = new Random();
        double buff = r.nextDouble() / 10;
        switch (type) {
            case HELM:
            buff = hero.maxHp * (0.075 + buff);
            break;
            case ARMOUR:
            buff = hero.def * (0.075 + buff);
            break;
            case WEAPON:
            buff = hero.att * (0.075 + buff);
            break;
        }
        return (int) buff;
    }

    ArtifactTypes getArtiType() {
        Random r = new Random();
        Integer type = r.nextInt(3);
        switch (type) {
        case 0:
            return ArtifactTypes.HELM;
        case 1:
            return ArtifactTypes.ARMOUR;
        case 2:
            return ArtifactTypes.WEAPON;
        default:
            return null;
        }
    }
}