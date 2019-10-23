package com.swingy.models;

public enum FighterTypes {
    PALADIN, ROGUE, WIZARD, WRAITH, MINDFLAYER, TROLL;

    public static boolean contains(String test) {
        for (FighterTypes c : FighterTypes.values())
            if (c.name().equals(test))
                return true;
        return false;
    }
}