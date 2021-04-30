package cu.curso.estudio.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import cu.curso.estudio.Entity.Usuario;
import cu.curso.estudio.Entity.ValidatorError;
import cu.curso.estudio.Repository.UsuarioRepository;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/AdminUser")
public class UsuarioController {
   @Autowired
  UsuarioRepository usuarios;

  @GetMapping(value = "/usuarios")
   public ResponseEntity<Map<String, List<Usuario>>>DevolverUsuarios(){
      HashMap<String,List<Usuario>> map = new HashMap<>();
      map.put("user.list", usuarios.findAll() );
      return new ResponseEntity<Map<String, List<Usuario>>>(map, HttpStatus.OK );
   }
      @PostMapping(value = "/crear")
     @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String,Object>> CrearUsuario(@Valid @RequestBody Usuario u, BindingResult result){
          HashMap<String,Object> map = new HashMap<>();
          if (result.hasErrors()){
              map.put("usuario",u);

          //  map.put("error",result.getAllErrors().stream().collect(Collectors.toList()));
              map.put("error",result.getFieldErrors().stream().map(this::mapError).collect(Collectors.toList()));
          }else{
         map.put("usuario",usuarios.save(u));
         map.put("mesagge","Usuario Insertado");}
        return new ResponseEntity<Map<String,Object>>( map, HttpStatus.OK);
        //  return usuarios.save(u);



    }

     private ValidatorError mapError(ObjectError objectError) {
         if (objectError instanceof FieldError) {
             return new ValidatorError(((FieldError) objectError).getField(),
                     objectError.getDefaultMessage());
         }
         return new ValidatorError(objectError.getObjectName(), objectError.getDefaultMessage());
     }



}
