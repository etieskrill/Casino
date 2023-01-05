package net.ictcampus.javamodul.casino.game;

import net.ictcampus.javamodul.casino.person.Player;

import java.util.Locale;

import static net.ictcampus.javamodul.util.ANSI.ANSI_GREEN_ITALIC;
import static net.ictcampus.javamodul.util.ANSI.ANSI_RESET;

public class LuckySeven extends Game {

    public static final int DEFAULT_STAKE = 10;

    private int potMult = 0;

    public LuckySeven(String name) {
        this(name, 1, 1);
    }

    public LuckySeven(String name, int numMinPlayers, int numMaxPlayers) {
        super(name, numMinPlayers, numMaxPlayers);
    }

    @Override
    public int requestStake(Player player) {
        return DEFAULT_STAKE;
    }

    @Override
    public boolean play() {
        int die1 = random.nextInt(1, 7), die2 = random.nextInt(1, 7);

        System.out.println(ANSI_GREEN_ITALIC + "Welcome to the game of Lucky Seven. \nIf you want an" +
                " explanation, please type \"yes\", otherwise \"no\"." + ANSI_RESET);
        String str;
        do {
            str = scanner.next().toLowerCase(Locale.ROOT);
        } while (!(str.equals("yes") || str.equals("no")));

        if (str.equals("yes")) {
            System.out.println(ANSI_GREEN_ITALIC +"You submit a guess that lies between 2 and 12." + ANSI_RESET); //TODO explain rulez
        }

        System.out.println(ANSI_GREEN_ITALIC + "\nPlease enter your guess: " + ANSI_RESET);
        str = scanner.next();
        int guess;

        while(true) {
            try {
                guess = Integer.parseInt(str);

                if (guess > 12 || guess < 2) {
                    System.out.println(ANSI_GREEN_ITALIC + "Your guess was outside of the range." +
                            ANSI_RESET);
                }

                int sum = die1 + die2;

                switch (guess) {
                    case 7 -> {
                        if (sum != 7) return false;
                        System.out.println(ANSI_GREEN_ITALIC + "You have won. The sum was indeed 7.");
                        potMult = 3;
                        return true;
                    }
                    case 2, 5, 8, 10, 11 -> {
                        switch (sum) {
                            case 2, 5, 8, 10, 11 -> {
                                System.out.println(ANSI_GREEN_ITALIC + "You have won. The sum was " + sum + ".");
                                potMult = 2;
                                return true;
                            }
                        }
                    }
                    case 3, 4, 6, 9, 12 -> {
                        switch (sum) {
                            case 3, 4, 6, 9, 12 -> {
                                System.out.println(ANSI_GREEN_ITALIC + "You have won. The sum was " + sum + ".");
                                potMult = 2;
                                return true;
                            }
                        }
                    }
                }

                return false;
            } catch (NumberFormatException ex) {
                System.out.println(ANSI_GREEN_ITALIC + "Please enter a valid guess." + ANSI_RESET);
            }
        }
    }

    @Override
    public int payWin(int pot) {
        int _potMult = potMult;
        potMult = 0;
        return pot * _potMult;
    }

}
