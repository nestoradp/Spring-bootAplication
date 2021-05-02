package cu.curso.estudio.Controller;

import cu.curso.estudio.Entity.Rol;
import cu.curso.estudio.Entity.ValidatorError;
import cu.curso.estudio.Repository.RolRepositori;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.servlet.annotation.HttpConstraint;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/AdminRol")

public class RolController {
    @Autowired
    RolRepositori rol;


    @PostMapping(value = "/crear")
    public ResponseEntity<Map<String, Object>> CrearRol(@Valid @RequestBody Rol r, BindingResult result) {
        HashMap<String, Object> map = new HashMap<>();
        if (result.hasErrors()) {
            map.put("rol", r);
            map.put("error", result.getAllErrors().stream().map(this::mapError).collect(Collectors.toList()));

        } else {
            map.put("rol", rol.save(r));
            map.put("message", "Rol Insertado correctamente");


        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }

    @GetMapping(value = "/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String,Object>>ListarRoles(){
    HashMap<String,Object> map = new HashMap<>();
      map.put("rol",rol.findAll());
      return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);

    }







    private ValidatorError mapError(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            return new ValidatorError(((FieldError) objectError).getField(),
                    objectError.getDefaultMessage());
        }
        return new ValidatorError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

}