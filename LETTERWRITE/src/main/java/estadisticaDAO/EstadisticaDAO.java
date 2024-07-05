package estadisticaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadisticaDAO implements EstadisticaDAOInterface{
    
    private static final String SQL_INSERT = 
            "insert into Estadistica(id_Estadisticas, progreso, act_hechasE, id_T, id_A, id_E, id_J) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = 
            "update Estadistica set progreso = ?, act_hechasE = ? where id_T = ? and id_A = ? and id_E = ? and id_J = ?";
    private static final String SQL_DELETE = 
            "delete from Estadistica where (id_T = ? and id_A = ? and id_E = ? and id_J = ?)";
    private static final String SQL_READ = 
            "select * from Estadistica where (id_T = ? and id_A = ? and id_E = ?)";

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
            Logger.getLogger(EstadisticaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void desconectar(){
        try{
            conexion.close();
        } catch (SQLException ex){
            Logger.getLogger(EstadisticaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addEstadistica(Estadistica estadistica) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setInt(1, estadistica.getIdEstadistica());
            ps.setString(2, estadistica.getProgreso());
            ps.setInt(3, estadistica.getActHechas());
            ps.setInt(4, estadistica.getIdTutor());
            ps.setInt(5, estadistica.getIdAlumno());
            ps.setInt(6, estadistica.getIdEscritura());
            ps.setInt(7, estadistica.getIdJuego());
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
    public void updateEstadistica(Estadistica estadistica) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, estadistica.getProgreso());
            ps.setInt(2, estadistica.getActHechas());
            ps.setInt(3, estadistica.getIdTutor());
            ps.setInt(4, estadistica.getIdAlumno());
            ps.setInt(5, estadistica.getIdEscritura());
            ps.setInt(6, estadistica.getIdJuego());
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
    public void deleteEstadistica(Estadistica estadistica) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, estadistica.getIdTutor());
            ps.setInt(2, estadistica.getIdAlumno());
            ps.setInt(3, estadistica.getIdEscritura());
            ps.setInt(4, estadistica.getIdJuego());
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
    public Estadistica getEstadisticaWithId(Estadistica estadistica) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, estadistica.getIdTutor());
            ps.setInt(2, estadistica.getIdAlumno());
            ps.setInt(3, estadistica.getIdEscritura());
            ps.setInt(4, estadistica.getIdJuego());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (!resultados.isEmpty()) {
                return (Estadistica) resultados.get(0);
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
            Estadistica estadistica = new Estadistica();
            estadistica.setIdEstadistica(rs.getInt("id_Estadisticas"));
            estadistica.setProgreso(rs.getString("progreso"));
            estadistica.setActHechas(rs.getInt("act_hechasE"));
            estadistica.setIdTutor(rs.getInt("id_T"));
            estadistica.setIdAlumno(rs.getInt("id_A"));
            estadistica.setIdEscritura(rs.getInt("id_E"));
            estadistica.setIdJuego(rs.getInt("id_J"));
            resultado.add(estadistica);
        }
        return resultado;
    }
    
}
