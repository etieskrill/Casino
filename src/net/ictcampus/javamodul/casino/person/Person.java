package net.ictcampus.javamodul.casino.person;

public abstract class Person {

    private final int id;
    private String lastName;
    private String firstName;
    private final int birthYear;

    public Person(int id, String lastName, String firstName, int birthYear) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthYear = birthYear;
    }

    public void sayName() {
        System.out.println(this);
    }

    public int getId() {
        return id;
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
