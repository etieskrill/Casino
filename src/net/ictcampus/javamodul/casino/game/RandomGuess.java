package net.ictcampus.javamodul.casino.game;

import net.ictcampus.javamodul.casino.person.Player;

import java.util.Locale;

import static net.ictcampus.javamodul.util.ANSI.ANSI_GREEN_ITALIC;
import static net.ictcampus.javamodul.util.ANSI.ANSI_RESET;

public class RandomGuess extends Game {

    public static final int DEFAULT_STAKE = 10;

    private int guesses = 1;

    public RandomGuess(String name) {
        this(name, 1, 1);
    }

    public RandomGuess(String name, int numMinPlayers, int numMaxPlayers) {
        super(name, numMinPlayers, numMaxPlayers);
    }

    @Override
    public int requestStake(Player player) {
        return DEFAULT_STAKE;
    }

    @Override
    public boolean play() {
        int r = random.nextInt(1, 101);

        System.out.println(ANSI_GREEN_ITALIC + "Welcome to the random number guessing game. \nIf you want an" +
                " explanation, please type \"yes\", otherwise \"no\"." + ANSI_RESET);
        String str;
        do {
            str = scanner.next().toLowerCase(Locale.ROOT);
        } while (!(str.equals("yes") || str.equals("no")));

        if (str.equals("yes")) {
            System.out.println(ANSI_GREEN_ITALIC +"You submit a guess that lies between 1 and 100. If you guessed the" +
                    "same number as we did, you win. For every unsuccessful guess your payout will decrease. You will" +
                    "be told if your guess was above or below ours. Only answers of integer format will be accepted." +
                    ANSI_RESET);
        }

        System.out.println(ANSI_GREEN_ITALIC + "\nPlease enter your guess: " + ANSI_RESET);
        str = scanner.next();
        int guess;

        while (true) { //TODO i know, i too am on the brink of puking
            try {
                guess = Integer.parseInt(str);

                if (guess > 100 || guess < 1) {
                    System.out.println(ANSI_GREEN_ITALIC + "Your guess was outside of the range." +
                            ANSI_RESET);
                }
                if (guess == r) {
                    System.out.println(ANSI_GREEN_ITALIC + "You have won. Our number was " + r + ".");
                    return true;
                } else {
                    System.out.println(ANSI_GREEN_ITALIC + "Not correct. Our number is " +
                            (guess > r ? "higher" : "lower") + " than what you guessed.");
                }

                System.out.println(ANSI_GREEN_ITALIC + "You have guessed " + guesses + " times." + ANSI_RESET);

                guesses++;
            } catch (NumberFormatException ex) {
                System.out.println(ANSI_GREEN_ITALIC + "Please enter a valid guess." + ANSI_RESET);
            }

            str = scanner.next();
        }
    }

    @Override
    public int payWin(int pot) {
        int _guesses = guesses;
        guesses = 1;
        return (6*pot) / _guesses;
    }

}
