package com.swingy.models;

/**
 * Fighter
 */
public class Fighter {
    public FighterTypes fighterType;

    public int level;
    public int att;
    public int def;
    public int maxHp;
    public int hp;

    protected Fighter() {
    }

    Fighter(String classType, int level) {
        this.fighterType = setFighterClass(classType);
        this.level = level;
        switch (this.fighterType) {
        case PALADIN:
            this.att = 50;
            this.def = 50;
            this.maxHp = 100;
            break;
        case ROGUE:
            this.att = 100;
            this.def = 50;
            this.maxHp = 50;
            break;
        case WIZARD:
            this.att = 50;
            this.def = 100;
            this.maxHp = 50;
            break;
        default:
            this.att = 100;
            this.def = 100;
            this.maxHp = 100;
            break;
        }
        this.hp = this.maxHp;
    }

    Fighter (String classType, Hero hero) {
        this.fighterType = setFighterClass(classType);
        switch (this.fighterType) {
        case WRAITH:
            this.att = hero.att / 4;
            this.def = hero.def / 4;
            this.maxHp = hero.maxHp / 2;
            break;
        case MINDFLAYER:
            this.att = hero.att / 2;
            this.def = hero.def / 4;
            this.maxHp = hero.maxHp / 4;
            break;
        case TROLL:
            this.att = hero.att / 4;
            this.def = hero.def / 2;
            this.maxHp = hero.maxHp / 4;
            break;
        default:
            this.att = hero.att / 2;
            this.def = hero.def / 2;
            this.maxHp = hero.maxHp / 2;
            break;
        }
        this.hp = this.maxHp;
    }

    private FighterTypes setFighterClass(String classType) {
        for (FighterTypes c : FighterTypes.values()) {
            if (c.name().equals(classType)) {
                return c;
            }
        }
        return FighterTypes.MINDFLAYER;
    }
}