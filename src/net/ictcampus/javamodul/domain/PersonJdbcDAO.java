package net.ictcampus.javamodul.domain;

import net.ictcampus.javamodul.casino.person.Person;
import net.ictcampus.javamodul.casino.person.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonJdbcDAO implements JdbcDAO<Person> {

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

    //So much for over-engineering. If this isn't a thing of beauty, then I don't know what is.
    @Override
    public <U> Person selectByProperty(String column, U arg) throws SQLException, IllegalArgumentException {
        con = ConnectionFactory.getInstance().getConnection();
        ps = con.prepareStatement("SELECT * FROM PERSON WHERE " + column + " = ?");
        if (arg instanceof Integer i) ps.setInt(1, i);
        else if (arg instanceof String str) ps.setString(1, str);
        else if (arg instanceof Date date) ps.setDate(1, date);
        else if (arg instanceof Float f) ps.setFloat(1, f);
        else throw new IllegalArgumentException("Type " + arg.getClass() + " could not be classified");

        rs = ps.executeQuery();
        ps.closeOnCompletion();
        if (rs == null) throw new SQLException("Query was not successful");

        //This method also throws an SQLException if there are no more results ... i think???
        //That assumption is based on whether a "closed result set" means the set has no more elements
        rs.next(); //Move pointer to first row
        //TODO separate tables for players and employees
        //TODO factory methods for players
        return new Player(
                rs.getInt("ID_Person"),
                rs.getString("name"),
                rs.getString("prename"),
                rs.getInt("birthyear"),
                rs.getInt("credit")
        );
    }

    @Override
    public Person selectByID(int id) throws SQLException {
        return selectByProperty("ID_Person", id);
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
