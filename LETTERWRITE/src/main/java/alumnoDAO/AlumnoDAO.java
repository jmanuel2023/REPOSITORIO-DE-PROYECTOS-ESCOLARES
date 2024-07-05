package alumnoDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoDAO implements AlumnoDAOInterface {

    private static final String SQL_INSERT = "insert into Alumno(id_A, nombre, contraseña, edad) values (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update Alumno set edad = ?, contraseña = ? where nombre = ?";
    private static final String SQL_DELETE = "delete from Alumno where id_A = ?";
    private static final String SQL_READ = "select * from Alumno where nombre = ?";

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
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addAlumno(Alumno alumno) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setInt(1, alumno.getIdA());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getPassword());
            ps.setInt(4, alumno.getEdad());
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

    //
    @Override
    public void updateAlumno(Alumno alumno, String newPass) throws SQLException {
        PreparedStatement ps = null;
        try {
            Alumno alumnoBD = this.getAlumnoWithName(alumno);
            if (alumno.getPassword().equals(alumnoBD.getPassword())) {
                conectar();
                ps = conexion.prepareStatement(SQL_UPDATE);
                ps.setInt(1, alumno.getEdad());
                if (newPass == null) {
                    ps.setString(2, alumno.getPassword());
                } else {
                    ps.setString(2, newPass);
                }
                ps.setString(3, alumno.getNombre());
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
    public void deleteAlumno(Alumno alumno) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, alumno.getIdA());
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
    public Alumno getAlumnoWithName(Alumno alumno) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setString(1, alumno.getNombre());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (!resultados.isEmpty()) {
                return (Alumno) resultados.get(0);
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
            Alumno alumno = new Alumno();
            alumno.setIdA(rs.getInt("id_A"));
            alumno.setNombre(rs.getString("nombre"));
            alumno.setEdad(rs.getInt("edad"));
            alumno.setPassword(rs.getString("contraseña"));
            resultado.add(alumno);
        }
        return resultado;
    }
/*
    public static void main(String[] args) {
        Alumno a = new Alumno();
        AlumnoDAO aDAO = new AlumnoDAO();
        a.setEdad(5);
        a.setIdA(1);
        a.setNombre("Juan");
        a.setPassword("1234");
        try {
            aDAO.addAlumno(a);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
}
