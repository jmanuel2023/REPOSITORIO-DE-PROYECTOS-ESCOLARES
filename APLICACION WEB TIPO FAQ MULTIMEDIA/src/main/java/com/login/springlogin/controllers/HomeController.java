package com.login.springlogin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/{path:[^\\.]*}") //Regex que dice que manejara cualquier ruta que no sea manejada por otro controller
    public String redirect() {
        // Redirige todas las solicitudes no manejadas por Spring hacia la ruta raíz "/" para que se manejen por react
        return "forward:/";
    }
}
 