package tutorDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TutorDAO implements TutorDAOInterface {

    private static final String SQL_INSERT = "insert into Tutor(id_A, nombreT, contraT) values (?, ?, ?)";
    private static final String SQL_UPDATE = "update Tutor set contraT = ? where nombreT = ?";
    private static final String SQL_DELETE = "delete from Tutor where nombreT = ?";
    private static final String SQL_READ = "select * from Tutor where nombreT = ?";

    private Connection conexion;

    private void conectar() {
        String usuario = "root";
        String clave = "1234";
        String url = "jdbc:mysql://localhost:3306/mydb";
        url = url + "?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useSSL=false";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addTutor(Tutor tutor) throws SQLException {
        //"insert into Tutor(id_A, nombreT, contraT) values (?, ?, ?)"
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setInt(1, tutor.getIdA());
            ps.setString(2, tutor.getNombre());
            ps.setString(3, tutor.getPassword());
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
    public void updateTutor(Tutor tutor, String newPass) throws SQLException {
        //"update Tutor set contraT = ? where nombreT = ?"
        PreparedStatement ps = null;
        try {
            Tutor tutorBD = this.getTutorWithName(tutor);
            if (tutor.getPassword().equals(tutorBD.getPassword())) {
                conectar();
                ps = conexion.prepareStatement(SQL_UPDATE);
                if(newPass == null){
                    ps.setString(1, tutor.getPassword());
                } else{
                    ps.setString(1, newPass);
                }
                ps.setString(2, tutor.getNombre());
                ps.executeUpdate();
            }
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
    public void deleteTutor(Tutor tutor) throws SQLException {
        //"delete from Tutor where nombreT = ?"
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setString(1, tutor.getNombre());
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
    public Tutor getTutorWithName(Tutor tutor) throws SQLException {
        //"select * from Tutor where nombreT = ?"
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setString(1, tutor.getNombre());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (!resultados.isEmpty()) {
                return (Tutor) resultados.get(0);
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
            Tutor tutor = new Tutor();
            tutor.setIdT(rs.getInt("id_T"));
            tutor.setIdA(rs.getInt("id_A"));
            tutor.setNombre(rs.getString("nombreT"));
            tutor.setPassword(rs.getString("contraT"));
            resultado.add(tutor);
        }
        return resultado;
    }
/*
    public static void main(String[] args) {
        Tutor a = new Tutor();
        TutorDAO aDAO = new TutorDAO();
        a.setIdA(1);
        a.setNombre("Pablo");
        a.setPassword("4567");
        try {
            aDAO.addTutor(a);
        } catch (SQLException ex) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
}
