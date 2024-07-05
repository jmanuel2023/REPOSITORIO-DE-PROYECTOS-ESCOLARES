package estadisticaDAO;

import java.sql.SQLException;

public interface EstadisticaDAOInterface {
    
    public void addEstadistica(Estadistica estadistica) throws SQLException;

    public void updateEstadistica(Estadistica estadistica) throws SQLException;

    public void deleteEstadistica(Estadistica estadistica) throws SQLException;

    public Estadistica getEstadisticaWithId(Estadistica estadistica) throws SQLException;
}
