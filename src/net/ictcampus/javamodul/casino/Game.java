package net.ictcampus.javamodul.casino;

import java.util.Random;

public class Game {

    private final String name;
    private final int numMinPlayers;
    private final int numMaxPlayers;

    //private final int Random random

    public Game(String name, int numMinPlayers, int numMaxPlayers) {
        this.name = name;
        this.numMinPlayers = numMinPlayers;
        this.numMaxPlayers = numMaxPlayers;
    }

    public boolean play() {
        return true;
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

    @Override
    public String toString() {
        return name;
    }

}
