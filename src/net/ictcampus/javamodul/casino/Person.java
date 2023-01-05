package net.ictcampus.javamodul.casino;

import java.util.Objects;

public class Person {

    public static final int STARTING_CREDIT = 50;

    private String lastName;
    private String firstName;
    private final int birthYear;
    private int credit;

    public Person(String lastName, String firstName, int birthYear) {
        this(lastName, firstName, birthYear, STARTING_CREDIT);
    }

    public Person(String lastName, String firstName, int birthYear, int credit) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthYear = birthYear;
        this.credit = credit;
    }

    public void sayName() {
        System.out.println(this);
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (birthYear != person.birthYear) return false;
        if (!lastName.equals(person.lastName)) return false;
        return firstName.equals(person.firstName);
    }

}
