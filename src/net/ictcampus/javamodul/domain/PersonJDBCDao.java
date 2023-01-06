package net.ictcampus.javamodul.domain;

import net.ictcampus.javamodul.casino.person.Person;
import net.ictcampus.javamodul.casino.person.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonJDBCDao implements JDBCDao<Person> {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public void insert(Person person) throws SQLException {
        con = ConnectionFactory.getInstance().getConnection();
        ps = con.prepareStatement("INSERT INTO PERSON (ID_Person, name, prename, birthyear) VALUES (?, ?, ?, ?)");
        ps.setInt(1, person.getId());
        ps.setString(2, person.getLastName());
        ps.setString(3, person.getFirstName());
        ps.setInt(4, person.getBirthYear());

        ps.executeUpdate();

        ps.closeOnCompletion();
    }

    @Override
    public void updateByID(int id, Person person) throws SQLException {
    }

    @Override
    public Person deleteByID(int id) throws SQLException {
        return null;
    }

    @Override
    public Person selectByID(int id) throws SQLException {
        con = ConnectionFactory.getInstance().getConnection();
        ps = con.prepareStatement("SELECT ID_Person, prename, name, birthyear, credit FROM PERSON WHERE ID_Person = ?");
        ps.setInt(1, id);

        rs = ps.executeQuery();
        ps.closeOnCompletion();

        rs.next(); //Move pointer to first row
        return new Player(
                rs.getInt("ID_Person"),
                rs.getString("name"),
                rs.getString("prename"),
                rs.getInt("birtyear"),
                rs.getInt("credit")
        );
    }

    @Override
    public List<Person> selectAll() throws SQLException {
        con = ConnectionFactory.getInstance().getConnection();
        ps = con.prepareStatement("SELECT ID_Person, prename, name, birthyear, credit FROM PERSON");

        rs = ps.executeQuery();
        ps.closeOnCompletion();

        List<Person> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Player(
                    rs.getInt("ID_Person"),
                    rs.getString("name"),
                    rs.getString("prename"),
                    rs.getInt("birthyear"),
                    rs.getInt("credit")
            ));
        }

        rs.close();
        return list;
    }

}
