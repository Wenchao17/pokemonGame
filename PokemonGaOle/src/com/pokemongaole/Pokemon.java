package com.pokemongaole;

import java.util.List;

public class Pokemon {
    private String name;
    private String defenderType;
    private List<Move> moves;
    private int hp;

    public Pokemon(String name, String defenderType, List<Move> moves, int hp) {
        this.name = name;
        this.defenderType = defenderType;
        this.moves = moves;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public String getDefenderType() {
        return defenderType;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public int getHp() {
        return hp;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public boolean isDefeated() {
        return this.hp == 0;
    }
}
