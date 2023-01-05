package net.ictcampus.javamodul.casino;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_ITALIC = "\033[0;32;3m";

    private final String name;
    private final int numMinPlayers;
    private final int numMaxPlayers;

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public Game(String name, int numMinPlayers, int numMaxPlayers) {
        this.name = name;
        this.numMinPlayers = numMinPlayers;
        this.numMaxPlayers = numMaxPlayers;
    }

    public boolean play() {
        int r = random.nextInt(1, 11);

        System.out.println(ANSI_GREEN_ITALIC + "Welcome to the random number guessing game. \nIf you want an" +
                " explanation, please type \"yes\", otherwise \"no\"." + ANSI_RESET);
        String str;
        do {
            str = scanner.next().toLowerCase(Locale.ROOT);
        } while (!(str.equals("yes") || str.equals("no")));

        if (str.equals("yes")) {
            System.out.println(ANSI_GREEN_ITALIC +"You submit a guess that lies between 1 and 10. If your guess is at" +
                    "most one away from ours, you win. If you guess outside of the valid range or your answer contains" +
                    "any characters outside of ASCII-decimals you will loose immediately." + ANSI_RESET);
        }

        System.out.println(ANSI_GREEN_ITALIC + "\nPlease enter your guess: " + ANSI_RESET);
        str = scanner.next();
        int guess;
        try {
            guess = Integer.parseInt(str);

            if (guess > 10 || guess < 1) {
                System.out.println(ANSI_GREEN_ITALIC + "You have lost because your guess was outside of the range." +
                        ANSI_RESET);
                return false;
            }
            if (guess - r > 1) {
                System.out.println(ANSI_GREEN_ITALIC + "You have lost. Our number was " + r + ".");
                return false;
            }

            System.out.println(ANSI_GREEN_ITALIC + "You have won. Our number was " + r + ".");
            return true;
        } catch (NumberFormatException ex) {
            System.out.println(ANSI_GREEN_ITALIC + "You have lost because your submission was of invalid format." +
                    ANSI_RESET);
            return false;
        }
    }

    public int payWin(int pot) {
        return 2 * pot;
    }

    public String getName() {
        return name;
    }

    public int getNumMinPlayers() {
        return numMinPlayers;
    }

    public int getNumMaxPlayers() {
        return numMaxPlayers;
    }

    public void setSeed(long seed) {
        this.random.setSeed(seed);
    }

    @Override
    public String toString() {
        return name;
    }

}
