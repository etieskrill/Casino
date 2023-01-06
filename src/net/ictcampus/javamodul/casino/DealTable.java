package net.ictcampus.javamodul.casino;

import net.ictcampus.javamodul.casino.game.Game;
import net.ictcampus.javamodul.casino.person.Employee;
import net.ictcampus.javamodul.casino.person.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static net.ictcampus.javamodul.util.ANSI.ANSI_GREEN_ITALIC;
import static net.ictcampus.javamodul.util.ANSI.ANSI_RESET;

public class DealTable {

    private final Game activity;
    private Employee croupier;
    private ArrayList<Player> players;
    private int pot;

    public DealTable(Game activity, Employee croupier) {
        this.activity = activity;
        this.croupier = croupier;
        this.players = new ArrayList<>();
    }

    @Deprecated
    private DealTable(Game activity, Employee croupier, Player... players) {
        this.activity = activity;
        this.croupier = croupier;
        if (players == null) {
            this.players = new ArrayList<>();
            return;
        }
        if (activity.getNumMinPlayers() > players.length || activity.getNumMaxPlayers() < players.length)
            throw new IllegalArgumentException("Illegal number of players for activity " + activity);
        //this.players = (ArrayList<Player>) Arrays.asList(players); //FIXME nani tf
    }

    public void play() {
        for (Player player : players) {
            addToPot(player.putAtStake(activity.requestStake(player)));
        }

        for (Player player : players) {
            System.out.println(ANSI_GREEN_ITALIC + "The pot currently amounts to " + getPot() + ".\n");
            System.out.println(ANSI_GREEN_ITALIC + "Currently playing: " + player.toString() + "\n");

            if (player.getCredit() <= 0) {
                System.out.println(ANSI_GREEN_ITALIC + player + " ist bankrott.");
                players.remove(player);
            }

            if (activity.play()) {
                player.earnMoney(activity.payWin(50));
            }

            System.out.println(ANSI_GREEN_ITALIC + "Your current credit amounts to " + player.getCredit() +
                    " doubloons.\n" + ANSI_RESET);

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
        for (Player player : players) {
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

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addPlayers(Player... players) {
        this.players.addAll(Arrays.asList(players));
    }

    public void addPlayers(Collection<Player> players) {
        this.players.addAll(players);
    }

    public boolean removePlayer(Player player) {
        return this.players.remove(player);
    }

    public Player removePlayer(int index) {
        return this.players.remove(index);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return this.players.size();
    }

}
