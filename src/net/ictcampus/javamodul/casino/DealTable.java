package net.ictcampus.javamodul.casino;

import java.util.Arrays;

public class DealTable {

    private final Game activity;
    private Person croupier;
    private Person[] players;

    public DealTable(Game activity, Person croupier, Person... players) {
        this.activity = activity;
        this.croupier = croupier;
        if (activity.getNumMinPlayers() > players.length || activity.getNumMaxPlayers() < players.length)
            throw new IllegalArgumentException("Illegal number of players for activity " + activity);
        this.players = players;
    }

    public void showInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
                "Das Spiel " + activity.toString() + " wird durch den Croupier " + croupier.toString() + " geführt.");
        for (Person player : players) {
            builder.append("\nEs spielt ").append(player.toString());
        }

        return builder.toString();
    }

}
