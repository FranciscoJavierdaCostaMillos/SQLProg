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

    private String url;
    private Connection conn = null;

    public BD(String url) {
        this.url = url;
    }

    public void createnewBD(String fileName) {

        String url = "jdbc:sqlite:" + fileName;

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

    public void connect() {
        conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:BD1.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void crearTablaProductos() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("CREATE TABLE ScoreTB (id UUID primary key, name String, score int);");
        } catch (SQLException ex) {
            System.err.println("Tabla ya creada");
        }
    }

    public void insertarProducto(UUID x,String y,int z) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into ScoreTB (id,name, score) values (?,?,?)");
            st.setString(1, x);
            st.setString(2, y);
            st.setInt(3, z);
            st.execute();
            System.out.println("Insertado correctamente");
        } catch (SQLException ex) {
            System.err.println("Registro no insertado");
        }
    }

    public void seleccionarProducto(String id) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "SELECT id,name,score "
                + "FROM productos WHERE name = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + "\t"
                    + rs.getInt("score"));
        }
    }

    public ResultSet getListaId() throws SQLException {
        Statement st = conn.createStatement();
        String sql = "SELECT id FROM productos";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        return rs;
    }

    public void update(String id, int precio, String nombreNuevo) throws SQLException {
        String sql = "UPDATE productos SET nombre = ? , "
                + "precio = ? "
                + "WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nombreNuevo);
        pstmt.setInt(2, precio);
        pstmt.setString(3, id);
        pstmt.executeUpdate();
    }
}
