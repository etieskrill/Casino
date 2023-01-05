package net.ictcampus.javamodul.casino;

public class Casino {

    public static void main(String[] args) {
        System.out.println("Juhui, wir haben folgende Personen erstellt: ");

        Person moritz = new Person("Meier", "Moritz", 2000);
        Person rita = new Person("Bürki", "Rita", 1975); //TODO parse non-ascii characters
        Person elvis = new Person("Presley", "Elvis", 1935);

        Person[] people = {moritz, rita, elvis};

        for (Person person : people) {
            person.sayName();
            System.out.println(person.getLastName() + " wurde im Jahr " + person.getBirthYear() + " geboren.");
        }

        Game numberGuessing = new Game("Zufallszahl", 1, 1);
        DealTable table = new DealTable(numberGuessing, rita, moritz);

        System.out.println("\n\nEinfache Casino-Simulation");
        System.out.println(table);
    }

}
