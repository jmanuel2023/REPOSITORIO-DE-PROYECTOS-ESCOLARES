package escrituraDAO;

import java.sql.SQLException;

public interface EscrituraLetraDAOInterface {
    
    public void addEscrituraLetra(EscrituraLetra el) throws SQLException;

    public void updateEscrituraLetra(EscrituraLetra el) throws SQLException;

    public void deleteEscrituraLetra(EscrituraLetra el) throws SQLException;

    public EscrituraNumero getEscrituraLetraWithId(EscrituraLetra el) throws SQLException;
}
