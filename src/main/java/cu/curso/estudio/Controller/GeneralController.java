package cu.curso.estudio.Controller;

import cu.curso.estudio.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@PreAuthorize("isAuthenticated()")
@RestController
public class GeneralController {

     @Autowired
    UserDetailsServiceImpl userDetailsService;


     @PreAuthorize("permitAll()")
    @GetMapping( value = "/inicio")
    public String inicio(){

         return userDetailsService.loadUserByUsername("yoleidi").getUsername();
     //    return"Bienvenidos";
    }


    @PreAuthorize("permitAll()")
    @GetMapping( value = "/login")
    public String login(){

      //  return userDetailsService.loadUserByUsername("yoleidi").getUsername();
           return"Bienvenidos";
    }



     @PreAuthorize("hasRole('Administrador')")
    @GetMapping( value = "/admin")
    public String Admin(){

         return"locos";
    }

}
