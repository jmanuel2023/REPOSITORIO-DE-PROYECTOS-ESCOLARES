package Servlets;

import alumnoDAO.Alumno;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IsLoged extends HttpServlet {
    
    private PrintWriter out;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        HttpSession session = request.getSession();
        
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        // {"id": int, "nombre": "true", "edad": int}
        if(alumno == null){
            out.write("{\"id\": \"null\", \"nombre\": \"null\", \"edad\": \"null\"}");
        } else { 
            out.write("{\"id\": " + alumno.getIdA() + ", \"nombre\": \"" + alumno.getNombre() + "\", \"edad\": " + alumno.getEdad() + "}");
        }
    }

}
