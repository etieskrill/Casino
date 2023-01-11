package net.ictcampus.javamodul.casino;

import net.ictcampus.javamodul.casino.game.Game;
import net.ictcampus.javamodul.casino.game.LuckySeven;
import net.ictcampus.javamodul.casino.person.Employee;
import net.ictcampus.javamodul.casino.person.Person;
import net.ictcampus.javamodul.casino.person.Player;
import net.ictcampus.javamodul.domain.PersonJdbcDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    private void demo() {
        PersonJdbcDAO personDao = new PersonJdbcDAO();

        List<Person> people;

        try {
            /*Person p = personDao.selectByProperty("name", "Hochueli");
            e1 = new Employee(p.getId(), p.getLastName(), p.getFirstName(), p.getBirthYear(), 10);
            p1 = (Player) personDao.selectByID(2);*/
            people = personDao.selectAll();
            if (people.size() < 2) {
                throw new RuntimeException("Insufficient number of people in database");
            }
        } catch (SQLException | RuntimeException ex) {
            System.err.println(ex.getMessage() + ", using defaults");
            people = new ArrayList<>();
            people.add(new Employee(1, "Maximoff", "Wanda", 1989, 100));
            people.add(new Player(2, "Barton", "Clint", 1975));
        }

        Person p = people.get(0);
        people.remove(0); //TODO pop
        Employee e1 = new Employee(p.getId(), p.getLastName(), p.getFirstName(), p.getBirthYear(), 10);

        Game numberGuessing = new LuckySeven("Lucky Seven", 1, 1);
        DealTable table = new DealTable(numberGuessing, e1);
        table.addPlayers(people.stream().map((person -> (Player) person)).toList());

        System.out.println("\n\nEinfache Casino-Simulation");
        System.out.println(table);

        String str;
        do {
            System.out.println();
            for (Player player : table.getPlayers()) {
                System.out.println(ANSI_GREEN_ITALIC + player.toString() + " currently has " + player.getCredit() +
                        " doubloons.");
            }
            System.out.println();

            table.play();
            System.out.println(ANSI_GREEN_ITALIC + "Would you like to play another game of " +
                    table.getActivity().toString() + "?" + ANSI_RESET);

            Scanner scanner = new Scanner(System.in);
            do {
                str = scanner.next().toLowerCase(Locale.ROOT);
            } while (!(str.equals("yes") || str.equals("no")));
        } while (str.equals("yes"));
    }

}
