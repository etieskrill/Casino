package net.ictcampus.javamodul.domain;

import java.sql.SQLException;
import java.util.List;

public interface JdbcDAO<T> {

    /**
     * <p>Insert a single object instance into the database.</p>
     *
     * @param t instance to insert
     * @throws SQLException if the transaction did not succeed
     */
    void insert(T t) throws SQLException;

    /**
     * <p>Update an already existing entry in the database.</p>
     *
     * @param id property by which to identify the database entry
     * @param t instance to update by
     * @throws SQLException if the transaction did not succeed
     */
    void updateByID(int id, T t) throws SQLException;

    /**
     * <p>Delete an existing entry in the database.</p>
     *
     * @param id property by which the entry is identified
     * @return an instance of the deleted entry
     * @throws SQLException if the transaction did not succeed
     */
    T deleteByID(int id) throws SQLException;

    /**
     * <p>Select all database entries which match the property.</p>
     *
     * @param column the column name in the database
     * @param arg property with datatype by which to select
     * @return the first matching database entry
     * @throws SQLException if the transaction did not succeed
     * @throws IllegalArgumentException if the variable type of {@code arg} is not supported
     */
    <U> T selectByProperty(String column, U arg) throws SQLException, IllegalArgumentException;

    /**
     * <p>Convenience method to select an entry with matching primary key ID.</p>
     *
     * @param id primary key by which the entry is identified
     * @return the first matching database entry
     * @throws SQLException if the transaction did not succeed
     */
    T selectByID(int id) throws SQLException;

    /**
     * <p>Select all table entries regardless of property.</p>
     *
     * @return all entries in the table
     * @throws SQLException if the transaction did not succeed
     */
    List<T> selectAll() throws SQLException;

}
