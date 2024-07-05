package escrituraDAO;

import java.io.Serializable;

public class EscrituraLetra extends Escritura implements Serializable {
    
    private int numAciertos;
    private int numErrores;

    public EscrituraLetra() {
        super();
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
       
}
