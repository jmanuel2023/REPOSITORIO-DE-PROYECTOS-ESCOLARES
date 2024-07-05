package Servlets;

import alumnoDAO.Alumno;
import alumnoDAO.AlumnoDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tutorDAO.Tutor;
import tutorDAO.TutorDAO;

@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {

    private PrintWriter out;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        Alumno alumno;
        Tutor tutor;
        
        Register req = getJson(request);
        alumno = registerAlumno(req);
        tutor = registerTutor(alumno, req);
        // {"isRegister": "true"}
        if(tutor != null && alumno != null){
            out.write("{\"isRegister\": \"true\"}");
        } else {
            out.write("{\"isRegister\": \"false\"}");
        }
    }
    
    private Alumno registerAlumno(Register req){
        Alumno alumno = new Alumno();
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        alumno.setNombre(req.getUserAlumno());
        alumno.setPassword(req.getPasswordAlumno());
        alumno.setEdad(req.getEdadAlumno());
        try {
            alumnoDAO.addAlumno(alumno);
            alumno = alumnoDAO.getAlumnoWithName(alumno);            
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;
    }
    
    private Tutor registerTutor(Alumno alumno, Register req){
        Tutor tutor = new Tutor();
        TutorDAO tutorDAO = new TutorDAO();
        tutor.setNombre(req.getNombreTutor());
        tutor.setPassword(req.getPasswordTutor());
        tutor.setIdA(alumno.getIdA());
        try {
            tutorDAO.addTutor(tutor);
            tutor = tutorDAO.getTutorWithName(tutor);
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tutor;
    }

    private Register getJson(HttpServletRequest request) throws IOException {
        StringBuilder jsonPayload = new StringBuilder();
        String line;
        Register req = null;
        try ( BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonPayload.append(line);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        if (jsonPayload.toString() != "") {
            req = objectMapper.readValue(jsonPayload.toString(), Register.class);
        }
        return req;
    }
}

class Register {

    private String userAlumno;
    private String passwordAlumno;
    private int edadAlumno;
    private String nombreTutor;
    private String passwordTutor;

    public String getUserAlumno() {
        return userAlumno;
    }

    public void setUserAlumno(String userAlumno) {
        this.userAlumno = userAlumno;
    }

    public String getPasswordAlumno() {
        return passwordAlumno;
    }

    public void setPasswordAlumno(String passwordAlumno) {
        this.passwordAlumno = passwordAlumno;
    }

    public int getEdadAlumno() {
        return edadAlumno;
    }

    public void setEdadAlumno(int edadAlumno) {
        this.edadAlumno = edadAlumno;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getPasswordTutor() {
        return passwordTutor;
    }

    public void setPasswordTutor(String passwordTutor) {
        this.passwordTutor = passwordTutor;
    }

}