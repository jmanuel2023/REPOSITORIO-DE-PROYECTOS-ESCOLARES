package historialDAO;

import java.io.Serializable;

public class Historial implements Serializable {

    private String idH;
    private String modoDeAprendizaje;   //Tentativa de quitar
    private int actHechas;
    private String idAlumno;
    
    public Historial(){
        
    }

    public String getIdH() {
        return idH;
    }

    public void setIdH(String idH) {
        this.idH = idH;
    }

    public String getModoDeAprendizaje() {
        return modoDeAprendizaje;
    }

    public void setModoDeAprendizaje(String modoDeAprendizaje) {
        this.modoDeAprendizaje = modoDeAprendizaje;
    }

    public int getActHechas() {
        return actHechas;
    }

    public void setActHechas(int actHechas) {
        this.actHechas = actHechas;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    

}
