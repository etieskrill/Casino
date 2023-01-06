package net.ictcampus.javamodul.domain;

import java.sql.SQLException;
import java.util.List;

public interface JDBCDao<T> {

    void insert(T t) throws SQLException;
    void updateByID(int id, T t) throws SQLException;
    T deleteByID(int id) throws SQLException;
    T selectByID(int id) throws SQLException;
    List<T> selectAll() throws SQLException;

}
