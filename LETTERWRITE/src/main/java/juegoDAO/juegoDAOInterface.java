package juegoDAO;

import java.sql.SQLException;


public interface juegoDAOInterface {
    
    public void addJuego(Juego juego) throws SQLException;

    public void updateJuego(Juego juego) throws SQLException;

    public void deleteJuego(Juego juego) throws SQLException;

    public Juego getJuegoWithId(Juego juego) throws SQLException;
    
    
    
}
