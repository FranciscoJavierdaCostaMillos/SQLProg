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
import javax.swing.JOptionPane;

/**
 *
 * @author guille
 */
public class BD {

    private String url = "jdbc:sqlite:BD1.db";
    private Connection conn=null;
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
        try{
          Class.forName("org.sqlite.JDBC");
          Connection con=DriverManager.getConnection("jdbc:sqlite:BD1.db");
          JOptionPane.showMessageDialog(null,"La conexión está establecida");
          return con;
      }  catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
          return null;
      }
    }


    public void crearTb() throws SQLException {
        String url = "jdbc:sqlite:BD1.db";

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


    public void insertarJugador(String x, String y, String z,Connection cn) throws SQLException {
        String sql = "INSERT INTO ScoreTB VALUES('"+x+"', '"+y+"',"+z+")";
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
         try {
        PreparedStatement pst = cn.prepareStatement("INSERT INTO personas VALUES (?,?,?)");
        pst.setString(1, x);
        pst.setString(2, y);
        pst.setString(3, z);
        pst.executeUpdate();
    } catch (Exception e) {
        System.out.print(e.getMessage());
    }
    }

}
