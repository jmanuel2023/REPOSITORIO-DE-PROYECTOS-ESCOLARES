package tutorDAO;

import java.sql.SQLException;

public interface TutorDAOInterface {
    
    public void addTutor(Tutor tutor) throws SQLException;

    public void updateTutor(Tutor tutor, String newPass) throws SQLException;

    public void deleteTutor(Tutor tutor) throws SQLException;

    public Tutor getTutorWithName(Tutor tutor) throws SQLException;

}
