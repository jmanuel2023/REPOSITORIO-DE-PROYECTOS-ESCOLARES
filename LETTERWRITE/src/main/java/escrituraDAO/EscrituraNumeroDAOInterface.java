package escrituraDAO;

import java.sql.SQLException;

public interface EscrituraNumeroDAOInterface {
    
    public void addEscrituraNumero(EscrituraNumero en) throws SQLException;

    public void updateEscrituraNumero(EscrituraNumero en) throws SQLException;

    public void deleteEscrituraNumero(EscrituraNumero en) throws SQLException;

    public EscrituraNumero getEscrituraNumeroWithId(EscrituraNumero en) throws SQLException;
}
