package com.pokemongaole;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Pokemon> capturedPokemon;
    private int score;

    public Player() {
        this.capturedPokemon = new ArrayList<>();
        this.score = 0;
    }

    public void catchPokemon(Pokemon pokemon) {
        if (capturedPokemon.size() < 3) {
            capturedPokemon.add(pokemon);
        }
    }

    public List<Pokemon> getCapturedPokemon() {
        return capturedPokemon;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return score;
    }
}
