package net.ictcampus.javamodul.casino.game;

import net.ictcampus.javamodul.casino.person.Player;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import static net.ictcampus.javamodul.util.ANSI.*;

public abstract class Game {

    protected final String name;
    protected final int numMinPlayers;
    protected final int numMaxPlayers;

    protected final Random random = new Random();
    protected final Scanner scanner = new Scanner(System.in);

    public Game(String name, int numMinPlayers, int numMaxPlayers) {
        this.name = name;
        this.numMinPlayers = numMinPlayers;
        this.numMaxPlayers = numMaxPlayers;
    }

    public abstract int requestStake(Player player);

    public abstract boolean play();

    public abstract int payWin(int pot);

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
