
package juegoDAO;
import java.io.Serializable;
import java.sql.Date;


public class Juego implements Serializable {
    
    private int idJ;
    private Date fecha;
    private String dificultad;
    private int numAciertos;
    private int numErrores;
    private String tipoJ;
    
    public Juego(){}

    public int getIdJ() {
        return idJ;
    }

    public void setIdJ(int idJ) {
        this.idJ = idJ;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getNumAciertos() {
        return numAciertos;
    }

    public void setNumAciertos(int numAciertos) {
        this.numAciertos = numAciertos;
    }
    
    public int getNumErrores() {
        return numErrores;
    }

    public void setNumErrores(int numErrores) {
        this.numErrores = numErrores;
    }
    
     public String getTipoJuego() {
        return tipoJ;
    }

    public void setTipoJuego(String tipoJ) {
        this.tipoJ = tipoJ;
    }
    
    
    
    
    
}
