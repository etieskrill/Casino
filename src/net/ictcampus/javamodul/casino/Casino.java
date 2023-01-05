package net.ictcampus.javamodul.casino;

import java.util.Locale;
import java.util.Scanner;

public class Casino {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_ITALIC = "\033[0;32;3m";

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

        String str;
        do {
            table.play();
            System.out.println(ANSI_GREEN_ITALIC + "Your current credit amounts to " + moritz.getCredit() + " doublons." +
                    "\nWould you like to play another game of " + table.getActivity().toString() + "?" + ANSI_RESET);
            Scanner scanner = new Scanner(System.in);
            do {
                str = scanner.next().toLowerCase(Locale.ROOT);
            } while (!(str.equals("yes") || str.equals("no")));
        } while (str.equals("yes"));
    }

}
