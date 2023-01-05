package net.ictcampus.javamodul.casino;

import net.ictcampus.javamodul.casino.game.Game;
import net.ictcampus.javamodul.casino.person.Employee;
import net.ictcampus.javamodul.casino.person.Player;

import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<Player> hasWon = new ArrayList<>(getNumberOfPlayers());

        for (Player player : players) {
            addToPot(player.putAtStake(activity.requestStake(player)));
        }

        for (Player player : players) {
            if (activity.play()) {
                hasWon.add(player);
            }
        }

        //Split rewards are rounded down
        int creditsPerPlayer = pot / hasWon.size();
        int rest = pot % creditsPerPlayer; //TODO rest payed back to casino or smthn idk
        for (Player player : hasWon) {
            player.earnMoney(creditsPerPlayer);
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

    public void addPlayers(Player... player) {
        this.players.addAll(Arrays.asList(player));
    }

    public boolean removePlayer(Player player) {
        return this.players.remove(player);
    }

    public Player removePlayer(int index) {
        return this.players.remove(index);
    }

    public int getNumberOfPlayers() {
        return this.players.size();
    }

}
