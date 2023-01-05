package net.ictcampus.javamodul.casino;

import net.ictcampus.javamodul.casino.game.Game;
import net.ictcampus.javamodul.casino.game.LuckySeven;
import net.ictcampus.javamodul.casino.person.Employee;
import net.ictcampus.javamodul.casino.person.Person;
import net.ictcampus.javamodul.casino.person.Player;

import java.util.Locale;
import java.util.Scanner;

import static net.ictcampus.javamodul.util.ANSI.ANSI_GREEN_ITALIC;
import static net.ictcampus.javamodul.util.ANSI.ANSI_RESET;

public class Casino {

    public Casino() {
        demo();
    }

    public static void main(String[] args) {
        Casino casino = new Casino();
    }

    private void comparison() {
        Person wanda = new Player("Maximoff", "Wanda", 1989);
        Person clint = new Player("Barton", "Clint", 1975);
        Person pietro = new Player("Maximoff", "Pietro", 1989);

        Person[] people = {wanda, clint, pietro};

        for (int i = 0; i < people.length - 1; i++) {
            for (int j = i + 1; j < people.length; j++) {
                Person p1 = people[i], p2 = people[j];
                System.out.println(p1.toString() + " and " + p2.toString() + " are" +
                        (p1.equals(p2) ? " " : " not ") + "the same person.");
            }
        }
    }

    private void demo() {
        System.out.println("Juhui, wir haben folgende Personen erstellt: ");

        //ofc these could all be players, but i hate unnecessary typecasting
        Player moritz = new Player("Meier", "Moritz", 2000);
        Employee rita = new Employee("Bürki", "Rita", 1975); //TODO parse non-ascii characters
        Player elvis = new Player("Presley", "Elvis", 1935);

        Person[] people = {moritz, rita, elvis};

        for (Person person : people) {
            person.sayName();
            System.out.println(person.getLastName() + " wurde im Jahr " + person.getBirthYear() + " geboren.");
        }

        Game numberGuessing = new LuckySeven("Lucky Seven", 1, 1);
        DealTable table = new DealTable(numberGuessing, rita);
        table.addPlayers(moritz);

        System.out.println("\n\nEinfache Casino-Simulation");
        System.out.println(table);

        String str;
        do {
            table.play();
            System.out.println(ANSI_GREEN_ITALIC + "Your current credit amounts to " + moritz.getCredit() +
                    " doublons.\nWould you like to play another game of " + table.getActivity().toString() + "?" +
                    ANSI_RESET);

            if (moritz.getCredit() <= 0) {
                System.out.println(ANSI_GREEN_ITALIC + "Moritz ist bankrott.");
                break;
            }

            Scanner scanner = new Scanner(System.in);
            do {
                str = scanner.next().toLowerCase(Locale.ROOT);
            } while (!(str.equals("yes") || str.equals("no")));
        } while (str.equals("yes"));
    }

}
