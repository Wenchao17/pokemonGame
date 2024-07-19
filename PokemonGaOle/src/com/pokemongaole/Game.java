package com.pokemongaole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private List<Pokemon> allPokemon;

    public Game() {
        player = new Player();
        allPokemon = generateAllPokemon();
    }

    public void start() {
        System.out.println("Welcome to Pokémon Ga-Ole!");
        chooseStage();
        catchInitialPokemon();
        battle();
    }

    private void chooseStage() {
        System.out.println("Choose a stage: beginner, intermediate, advanced, legendary");
        Scanner scanner = new Scanner(System.in);
        String stage = scanner.nextLine();
        System.out.println("You selected: " + stage);
        // Implement stage selection logic if needed
    }

    private void catchInitialPokemon() {
        System.out.println("Throw balls to catch Pokémon!");
        Scanner scanner = new Scanner(System.in);
        int numToCatch = 3;

        for (int i = 0; i < numToCatch; i++) {
            Pokemon randomPokemon = getRandomPokemon();
            System.out.println("You encountered a wild " + randomPokemon.getName() + "!");

            System.out.println("Do you want to catch this Pokémon? (yes/no)");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                player.catchPokemon(randomPokemon);
                System.out.println("You caught " + randomPokemon.getName() + "!");
            } else {
                System.out.println(randomPokemon.getName() + " ran away!");
            }
        }
    }

    private void battle() {
        if (player.getCapturedPokemon().isEmpty()) {
            System.out.println("No Pokémon available to battle. Please catch some Pokémon first.");
            return;
        }

        System.out.println("Choose your Pokémon for battle:");
        Pokemon playerPokemon = choosePokemon();

        System.out.println("Starting battle!");
        Pokemon wildPokemon = getRandomPokemon();

        Battle battle = new Battle(playerPokemon, wildPokemon);
        battle.start();

        if (battle.isPlayerWinner()) {
            System.out.println("You won the battle!");
            System.out.println("Do you want to catch the defeated Pokémon? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                System.out.println("Choose a Pokéball: (1) Standard (2) Great (3) Ultra");
                int ballChoice = scanner.nextInt();
                boolean caught = attemptCatch(wildPokemon, ballChoice);
                if (caught) {
                    player.catchPokemon(wildPokemon);
                    System.out.println("You caught " + wildPokemon.getName() + "!");
                } else {
                    System.out.println(wildPokemon.getName() + " escaped!");
                }
            }
        } else {
            System.out.println("You lost the battle...");
        }

        player.addScore(100); // Example score
        displayTopScores();
    }

    private boolean attemptCatch(Pokemon pokemon, int ballChoice) {
        Random random = new Random();
        int catchRate;
        switch (ballChoice) {
            case 2: // Great Ball
                catchRate = 70;
                break;
            case 3: // Ultra Ball
                catchRate = 90;
                break;
            default: // Standard Ball
                catchRate = 50;
                break;
        }
        return random.nextInt(100) < catchRate;
    }

    private Pokemon choosePokemon() {
        List<Pokemon> capturedPokemon = player.getCapturedPokemon();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < capturedPokemon.size(); i++) {
            System.out.println((i + 1) + ". " + capturedPokemon.get(i).getName());
        }
        int choice = scanner.nextInt();
        return capturedPokemon.get(choice - 1);
    }

    private List<Pokemon> generateAllPokemon() {
        List<Pokemon> pokemonList = new ArrayList<>();

        // Create some moves
        Move thunderbolt = new Move("Thunderbolt", 50, "Electric");
        Move flamethrower = new Move("Flamethrower", 60, "Fire");
        Move vineWhip = new Move("Vine Whip", 45, "Grass");
        Move waterGun = new Move("Water Gun", 40, "Water");
        Move quickAttack = new Move("Quick Attack", 30, "Normal");

        // Populate with example Pokémon
        pokemonList.add(new Pokemon("Pikachu", "Electric", Arrays.asList(thunderbolt), 100));
        pokemonList.add(new Pokemon("Charizard", "Fire", Arrays.asList(flamethrower), 120));
        pokemonList.add(new Pokemon("Bulbasaur", "Grass", Arrays.asList(vineWhip), 90));
        pokemonList.add(new Pokemon("Squirtle", "Water", Arrays.asList(waterGun), 95));
        pokemonList.add(new Pokemon("Eevee", "Normal", Arrays.asList(quickAttack), 85));

        return pokemonList;
    }

    private Pokemon getRandomPokemon() {
        Random random = new Random();
        return allPokemon.get(random.nextInt(allPokemon.size()));
    }

    private void displayTopScores() {
        System.out.println("Top Scores: ");
        System.out.println("Player: " + player.getScore());
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

