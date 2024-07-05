package escrituraDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class EscrituraNumeroDAO implements EscrituraNumeroDAOInterface {
    
    private static final String SQL_INSERT = "insert into EscrituraNumero(numL_acertadas, numL_erroneas, id_E) values (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update EscrituraNumero set nombre = ? where id_A = ? ";
    private static final String SQL_DELETE = "delete from EscrituraNumero where id_A = ?";
    private static final String SQL_READ = "select id_A, nombre, edad from EscrituraNumero where id_A = ?";
    
    private Connection conexion;

    @Override
    public void addEscrituraNumero(EscrituraNumero en) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateEscrituraNumero(EscrituraNumero en) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteEscrituraNumero(EscrituraNumero en) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EscrituraNumero getEscrituraNumeroWithId(EscrituraNumero en) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
