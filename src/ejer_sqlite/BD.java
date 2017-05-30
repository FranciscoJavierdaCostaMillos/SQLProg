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


    public void crearTb(Connection c) throws SQLException {
        Statement st = c.createStatement();
        try {
            st.execute("CREATE TABLE ScoreTB (id String primary key, name String, score String);");
            System.out.println("Table created");
        } catch (SQLException ex) {
            System.err.println("Tabla ya creada");
}
    }


    public void insertarJugador(String x, String y, String z,Connection cn) throws SQLException {
          try {
            PreparedStatement st = cn.prepareStatement("insert into ScoreTB (id,name,Score) values (?,?,?)");
            st.setString(1, x);
            st.setString(2, y);
            st.setString(3, z);
            st.execute();
            System.out.println("Insertado correctamente");
        } catch (SQLException ex) {
            System.err.println("Registro no insertado");
}
    }

}
