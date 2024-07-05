package alumnoDAO;

import java.sql.SQLException;

public interface AlumnoDAOInterface {
    
    public void addAlumno(Alumno alumno) throws SQLException;

    public void updateAlumno(Alumno alumno, String newPass) throws SQLException;

    public void deleteAlumno(Alumno alumno) throws SQLException;

    public Alumno getAlumnoWithName(Alumno alumno) throws SQLException;

}
