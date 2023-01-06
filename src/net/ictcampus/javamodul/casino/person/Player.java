package net.ictcampus.javamodul.casino.person;

public class Player extends Person {

    public static final int STARTING_CREDIT = 50;

    private int credit;

    public Player(int id, String lastName, String firstName, int birthYear) {
        this(id, lastName, firstName, birthYear, STARTING_CREDIT);
    }

    public Player(int id, String lastName, String firstName, int birthYear, int credit) {
        super(id, lastName, firstName, birthYear);
        this.credit = credit;
    }

    public void earnMoney(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Negative amount of money");
        credit += amount;
    }

    public int putAtStake(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Negative amount of money");
        if (amount > credit) return 0;
        credit -= amount;
        return amount;
    }

    public int getCredit() {
        return credit;
    }

}
