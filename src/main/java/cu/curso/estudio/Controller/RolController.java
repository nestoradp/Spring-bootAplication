package cu.curso.estudio.Controller;

import cu.curso.estudio.Entity.Rol;
import cu.curso.estudio.Repository.RolRepositori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/adminRol")

public class RolController {
    @Autowired
    RolRepositori rol;


    @PostMapping(value = "/crear")
  public Rol CrearRol(@RequestBody Rol r){
      return rol.save(r);
  }



}
