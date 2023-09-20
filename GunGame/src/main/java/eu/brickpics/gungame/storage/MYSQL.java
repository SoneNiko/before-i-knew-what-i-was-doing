package eu.brickpics.gungame.storage;

import eu.brickpics.gungame.manager.util.Log;

import java.sql.*;

public class MYSQL {
    private String HOST = "";
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";
    private Connection con;

    public MYSQL(String host, String database, String user, String password) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
        connect();
    }

    public void connect() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=false", this.USER, this.PASSWORD);
            Log.info("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
        } catch (SQLException e) {
            Log.severe("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (this.con != null) {
                this.con.close();
                Log.info("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        } catch (SQLException e) {
            Log.severe("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }

    public void update(String qry) {
        try {
            Statement st = this.con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = this.con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return rs;
    }
}
