package net.ictcampus.javamodul.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private String dbUrl;
    private String dbUser;
    private String dbPwd;

    private static ConnectionFactory factory;

    private ConnectionFactory(String dbUrl, String dbUser, String dbPwd) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
    }

    public static ConnectionFactory getInstance() {
        if (factory == null) {
            factory = new ConnectionFactory("jdbc:mysql://localhost:3306/CASINO", "javaClient", "1234");
        }

        return factory;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPwd());
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

}
