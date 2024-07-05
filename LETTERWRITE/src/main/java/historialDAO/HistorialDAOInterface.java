package historialDAO;

import java.sql.SQLException;

public interface HistorialDAOInterface {
    
    public void addHistorial(Historial historial) throws SQLException;

    public void updateHistorial(Historial historial) throws SQLException;

    public void deleteHistorial(Historial historial) throws SQLException;

    public Historial getHistorialWithId(Historial historial) throws SQLException;
}
