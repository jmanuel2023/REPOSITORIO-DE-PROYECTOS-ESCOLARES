/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JuegoDAO implements juegoDAOInterface {
    
    private static final String SQL_INSERT = "insert into JUEGOS(id_J, fecha, dificultad, num_aciertos, num_errores, tipoJ) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update JUEGOS set num_aciertos = ?, num_errores=? where id_J = ? ";
    private static final String SQL_DELETE = "delete from JUEGOS where id_J = ?";
    private static final String SQL_READ = "select * from JUEGOS where id_J = ?";
    
    private Connection conexion;
    
    private void conectar(){
        String usuario = "root";
        String clave = "1234";
        String url = "jdbc:mysql://localhost:3306/mydb";
        //?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useSSL=false
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JuegoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void desconectar(){
        try{
            conexion.close();
        } catch (SQLException ex){
            Logger.getLogger(JuegoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addJuego(Juego juego) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setInt(1, juego.getIdJ());
            ps.setDate(2, juego.getFecha());
            ps.setString(3, juego.getDificultad());
            ps.setInt(4, juego.getNumAciertos());
            ps.setInt(5, juego.getNumErrores());
            ps.setString(6, juego.getTipoJuego());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                desconectar();
            }
        }
    }

    @Override
    public void updateJuego(Juego juego) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setInt(1, juego.getNumAciertos());
            ps.setInt(2, juego.getNumErrores());
            ps.setInt(3, juego.getIdJ());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                desconectar();
            }
        }
    }

    @Override
    public void deleteJuego(Juego juego) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, juego.getIdJ());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                desconectar();
            }
        }
    }

    @Override
    public Juego getJuegoWithId(Juego juego) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, juego.getIdJ());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (!resultados.isEmpty()) {
                return (Juego) resultados.get(0);
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                desconectar();
            }
        }
    }
    
    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultado = new ArrayList();
        while (rs.next()) {
            Juego juego = new Juego();
            juego.setIdJ(rs.getInt("id_J"));
            juego.setFecha(rs.getDate("fecha"));
            juego.setDificultad(rs.getString("dificultad"));
            juego.setNumAciertos(rs.getInt("num_aciertos"));
            juego.setNumErrores(rs.getInt("num_aciertos"));
            juego.setTipoJuego(rs.getString("tipoJ"));
            
            resultado.add(juego);
        }
        return resultado;
    }
    
}
