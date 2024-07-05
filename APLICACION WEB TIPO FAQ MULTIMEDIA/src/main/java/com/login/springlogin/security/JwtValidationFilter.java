package com.login.springlogin.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.springlogin.enums.JWTEnums;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class JwtValidationFilter extends BasicAuthenticationFilter{

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

                //Permitir el acceso a raiz para mostrar lo de react
                if (isRoot(request)) {
                    chain.doFilter(request, response);
                    return;
                }
        //Obtenemos el header de la petición (authorization)
        String header = request.getHeader(JWTEnums.CREATE_AUTHORIZATION.getValue());
        //Obtenemos el token del header de la respuesta
        String token = header.replace(JWTEnums.PREFIX_TOKEN.getValue(), "");
       
        //Validamos si el header es nulo o no comienza con el prefijo Bearer
        if(header == null || !header.startsWith(JWTEnums.PREFIX_TOKEN.getValue())){
            //chain.doFilter(request, response);
            return;
        }
        try {
        Claims claims = Jwts.parser().verifyWith(JWTKey.SECRET_KEY).build().parseSignedClaims(token).getPayload();
        String email = (String)claims.get("email");
        //System.out.println("Email: " + email);
        //Validamos el token para autenticar al usuario(inica sesion)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, null);
        //Autenticamos al usuario
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);  
        } catch (Exception e) {
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "Token no valido");
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
        }
    }
    private boolean isRoot(HttpServletRequest request) {
        String uri = request.getRequestURI(); //obteniendo la ruta
        return uri.startsWith("/")  || uri.contains("/css/") || uri.contains("/js/");
    }
    
}
