package com.pokemongaole;

import java.util.Random;

public class Battle {
    private Pokemon playerPokemon;
    private Pokemon wildPokemon;

    public Battle(Pokemon playerPokemon, Pokemon wildPokemon) {
        this.playerPokemon = playerPokemon;
        this.wildPokemon = wildPokemon;
    }

    public void start() {
        Random random = new Random();

        while (!playerPokemon.isDefeated() && !wildPokemon.isDefeated()) {
            // Player's turn
            Move playerMove = playerPokemon.getMoves().get(random.nextInt(playerPokemon.getMoves().size()));
            int playerDamage = calculateDamage(playerMove, wildPokemon);
            wildPokemon.takeDamage(playerDamage);
            System.out.println(playerPokemon.getName() + " used " + playerMove.getName() + " and dealt " + playerDamage + " damage to " + wildPokemon.getName() + "!");

            if (wildPokemon.isDefeated()) break;

            // Wild Pokemon's turn
            Move wildMove = wildPokemon.getMoves().get(random.nextInt(wildPokemon.getMoves().size()));
            int wildDamage = calculateDamage(wildMove, playerPokemon);
            playerPokemon.takeDamage(wildDamage);
            System.out.println(wildPokemon.getName() + " used " + wildMove.getName() + " and dealt " + wildDamage + " damage to " + playerPokemon.getName() + "!");
        }
    }

    private int calculateDamage(Move move, Pokemon defender) {
        // Simplified damage calculation
        return move.getDamage();
    }

    public boolean isPlayerWinner() {
        return !playerPokemon.isDefeated() && wildPokemon.isDefeated();
    }
}
