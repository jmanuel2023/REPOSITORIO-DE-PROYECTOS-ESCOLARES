package historialDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistorialDAO implements HistorialDAOInterface {

    private static final String SQL_INSERT = "insert into Historial(id_H, modo de aprendizaje, act_hechas, ALUMNO_id_A) values (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update Historial set modo de aprendizaje = ?, act_hechas = ? where id_H = ? ";
    private static final String SQL_DELETE = "delete from Historial where id_H = ?";
    private static final String SQL_READ = "select * from Tutor where id_H = ?";

    private Connection conexion;

    private void conectar() {
        String usuario = "root";
        String clave = "1234";
        String url = "jdbc:mysql://localhost:3306/mydb";
        //?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useSSL=false
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HistorialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void desconectar(){
        try{
            conexion.close();
        } catch (SQLException ex){
            Logger.getLogger(HistorialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addHistorial(Historial historial) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, historial.getIdH());
            ps.setString(2, historial.getModoDeAprendizaje());
            ps.setInt(3, historial.getActHechas());
            ps.setString(4, historial.getIdAlumno());
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
    public void updateHistorial(Historial historial) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, historial.getModoDeAprendizaje());
            ps.setInt(2, historial.getActHechas());
            ps.setString(3, historial.getIdH());
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
    public void deleteHistorial(Historial historial) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setString(1, historial.getIdH());
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
    public Historial getHistorialWithId(Historial historial) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setString(1, historial.getIdH());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (!resultados.isEmpty()) {
                return (Historial) resultados.get(0);
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
            Historial historial = new Historial();
            historial.setIdH(rs.getString("id_H"));
            historial.setModoDeAprendizaje(rs.getString("modo de aprendizaje"));
            historial.setActHechas(rs.getInt("act_hechas"));
            historial.setIdAlumno(rs.getString("ALUMNO_id_A"));
            resultado.add(historial);
        }
        return resultado;
    }
}
