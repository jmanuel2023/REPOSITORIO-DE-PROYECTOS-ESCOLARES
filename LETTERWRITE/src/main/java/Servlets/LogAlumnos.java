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
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogAlumnos", urlPatterns = {"/LogAlumnos"})
public class LogAlumnos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        Alumno alumno = new Alumno();
        PrintWriter out = response.getWriter();
        Request req = getJson(request);

        alumno.setNombre(req.getUser());
        alumno.setPassword(req.getPassword());
        //json plantilla: {"existUsr": boolean, "passCorrect": boolean}
        try {
            Alumno alumnoBD = alumnoDAO.getAlumnoWithName(alumno);
            if (alumnoBD == null) {
                out.write("{\"existUsr\": \"false\", \"passCorrect\": \"false\"}");
            } else if (alumnoBD.getPassword().equals(alumno.getPassword())) {
                out.write("{\"existUsr\": \"true\", \"passCorrect\": \"true\"}");
                session.setAttribute("alumno", alumnoBD);
            } else {
                out.write("{\"existUsr\": \"true\", \"passCorrect\": \"false\"}");
            }
        } catch (SQLException e) {
            Logger.getLogger(LogAlumnos.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Request getJson(HttpServletRequest request) throws IOException {
        StringBuilder jsonPayload = new StringBuilder();
        String line;
        Request req = null;
        try ( BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonPayload.append(line);
            }
        }
        if (!"".equals(jsonPayload.toString())) {
            ObjectMapper objectMapper = new ObjectMapper();
            req = objectMapper.readValue(jsonPayload.toString(), Request.class);
        }
        return req;
    }
}

class Request {

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUsername(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
