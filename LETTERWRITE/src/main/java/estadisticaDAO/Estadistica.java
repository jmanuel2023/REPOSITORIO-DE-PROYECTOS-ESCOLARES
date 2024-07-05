package estadisticaDAO;

import java.io.Serializable;

public class Estadistica implements Serializable {

    private int idEstadistica;
    private String progreso;
    private int actHechas;
    private int idTutor;
    private int idAlumno;
    private int idEscritura;
    private int idJuego;

    public Estadistica() {
    }

    public int getIdEstadistica() {
        return idEstadistica;
    }

    public void setIdEstadistica(int idEstadistica) {
        this.idEstadistica = idEstadistica;
    }

    public String getProgreso() {
        return progreso;
    }

    public void setProgreso(String progreso) {
        this.progreso = progreso;
    }

    public int getActHechas() {
        return actHechas;
    }

    public void setActHechas(int actHechas) {
        this.actHechas = actHechas;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdEscritura() {
        return idEscritura;
    }

    public void setIdEscritura(int idEscritura) {
        this.idEscritura = idEscritura;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }
    
}
