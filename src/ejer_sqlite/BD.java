/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer_sqlite;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 *
 * @author guille
 */
public class BD {

    private String url = "jdbc:sqlite:BD1.db";
    private Connection conn;
    public static String db = "BD1.db";
    public BD() {
    }

    public void createnewBD(String fileName) {

        url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void crearTb() throws SQLException {
        String url = "jdbc:sqlite:" + db;

        String sql = "CREATE TABLE IF NOT EXISTS ScoreTB (\n"
                + "	id String PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	score text NOT NULL,\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void insertarJugador(String x, String y, int z) throws SQLException {
        String sql = "INSERT INTO ScoreTB VALUES('"+x+"', '"+y+"',"+z+")";
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);

    }

}
