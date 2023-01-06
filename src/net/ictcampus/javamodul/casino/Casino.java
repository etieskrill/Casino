package net.ictcampus.javamodul.casino;

import net.ictcampus.javamodul.casino.game.Game;
import net.ictcampus.javamodul.casino.game.LuckySeven;
import net.ictcampus.javamodul.casino.person.Employee;
import net.ictcampus.javamodul.casino.person.Person;
import net.ictcampus.javamodul.casino.person.Player;
import net.ictcampus.javamodul.domain.PersonJDBCDao;

import java.sql.SQLException;
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
        PersonJDBCDao dao = new PersonJDBCDao();

        Player p1;
        Player p2;

        try {
            p1 = (Player) dao.selectByID(1);
            p2 = (Player) dao.selectByID(2);
        } catch (SQLException ex) {
            System.err.println("Could not select players from database, using defaults");
            p1 = new Player("Wanda")
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
