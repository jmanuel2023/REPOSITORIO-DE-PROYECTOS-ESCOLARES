package escrituraDAO;

import java.io.Serializable;
import java.sql.Date;

public class Escritura implements Serializable {
    
    protected int idEscritura;
    protected Date fecha;
    protected int idAlumno;

    public Escritura() {
    }

    public int getIdEscritura() {
        return idEscritura;
    }

    public void setIdEscritura(int idEscritura) {
        this.idEscritura = idEscritura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    
}
