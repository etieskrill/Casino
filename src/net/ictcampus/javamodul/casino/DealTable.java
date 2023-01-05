package net.ictcampus.javamodul.casino;

import java.util.Arrays;

public class DealTable {

    public static final int DEFAULT_STAKE = 10;

    private final Game activity;
    private Person croupier;
    private Person[] players;
    private int pot;

    public DealTable(Game activity, Person croupier, Person... players) {
        this.activity = activity;
        this.croupier = croupier;
        if (activity.getNumMinPlayers() > players.length || activity.getNumMaxPlayers() < players.length)
            throw new IllegalArgumentException("Illegal number of players for activity " + activity);
        this.players = players;
    }

    public void play() {
        Person player = players[0];
        addToPot(player.putAtStake(DEFAULT_STAKE));

        if (activity.play()) {
            player.earnMoney(activity.payWin(pot));
        }

        setPot(0);
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

    public int getPot() {
        return pot;
    }

    public void addToPot(int amount) {
        this.pot += amount;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public Game getActivity() {
        return activity;
    }

}
