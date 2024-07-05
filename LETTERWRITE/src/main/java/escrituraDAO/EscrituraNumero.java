package escrituraDAO;

public class EscrituraNumero {
    
    private int numAciertos;
    private int numErrores;

    public EscrituraNumero() {
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
