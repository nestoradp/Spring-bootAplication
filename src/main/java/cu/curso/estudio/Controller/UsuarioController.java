package cu.curso.estudio.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import cu.curso.estudio.Entity.Usuario;
import cu.curso.estudio.Repository.UsuarioRepository;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.*;

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
    public ResponseEntity<Map<String,Object>> CrearUsuario(@RequestBody Usuario u){
        HashMap<String,Object> map = new HashMap<>();
         map.put("usuario",usuarios.save(u));
         map.put("mesagge","Usuario Insertado");
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
        //  return usuarios.save(u);



    }



}
