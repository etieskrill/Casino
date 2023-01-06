package net.ictcampus.javamodul.casino.person;

public class Employee extends Person {

    public static final int STARTING_WAGE = 10;

    private int wage;

    public Employee(String lastName, String firstName, int birthYear) {
        this(lastName, firstName, birthYear, STARTING_WAGE);
    }

    public Employee(String lastName, String firstName, int birthYear, int wage) {
        super(-1, lastName, firstName, birthYear);
        this.wage = wage;
    }

    public void sayWelcome() {
        System.out.println("Welcome to the Grand Casino.");
    }

}
