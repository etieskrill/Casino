package net.ictcampus.javamodul.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DealTable {

    private final Game activity;
    private Person croupier;
    private ArrayList<Person> players;
    private int pot;

    public DealTable(Game activity, Person croupier) {
        this(activity, croupier, (Person) null); //FIXME what even is this
    }

    private DealTable(Game activity, Person croupier, Person... players) {
        this.activity = activity;
        this.croupier = croupier;
        if (players == null) {
            this.players = new ArrayList<>();
            return;
        }
        if (activity.getNumMinPlayers() > players.length || activity.getNumMaxPlayers() < players.length)
            throw new IllegalArgumentException("Illegal number of players for activity " + activity);
        this.players = (ArrayList<Person>) Arrays.asList(players);
    }

    public void play() {
        ArrayList<Person> hasWon = new ArrayList<>(getNumberOfPlayers());

        for (Person player : players) {
            addToPot(player.putAtStake(activity.requestStake(player)));
        }

        for (Person player : players) {
            if (activity.play()) {
                hasWon.add(player);
            }
        }

        //Split rewards are rounded down
        int creditsPerPlayer = pot / hasWon.size();
        int rest = pot % creditsPerPlayer; //TODO rest payed back to casino or smthn idk
        for (Person player : hasWon) {
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

    public void addPlayer(Person player) {
        this.players.add(player);
    }

    public void addPlayers(Person... player) {
        this.players.addAll(Arrays.asList(player));
    }

    public boolean removePerson(Person person) {
        return this.players.remove(person);
    }

    public Person removePerson(int index) {
        return this.players.remove(index);
    }

    public int getNumberOfPlayers() {
        return this.players.size();
    }

}
