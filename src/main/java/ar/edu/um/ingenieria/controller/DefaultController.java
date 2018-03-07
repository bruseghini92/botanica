package ar.edu.um.ingenieria.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } 
        if (request.isUserInRole("ROLE_USER")) {
        return "redirect:/seguimientos";
        }
        return "redirect:/ventas";
    }
}