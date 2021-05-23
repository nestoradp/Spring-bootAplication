package cu.curso.estudio.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import cu.curso.estudio.DTO.ChangePasswordDTO;
import cu.curso.estudio.Entity.Usuario;
import cu.curso.estudio.Entity.ValidatorError;
import cu.curso.estudio.Repository.UsuarioRepository;
import cu.curso.estudio.service.UsuarioService;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.XpathAssertions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.*;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('Administrador')")
@RestController
@RequestMapping(path = "/AdminUser")
public class UsuarioController {
   @Autowired
  UsuarioRepository usuarios;
   @Autowired
   UsuarioService usuarioService;


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
           if(CheckUserName(u)){
               ObjectError errorUsuario = new ObjectError("usuario","El usuario ya existe");
               result.addError(errorUsuario);
           }
         if(ChekearPasswordMatch(u)){
             ObjectError errorPass = new ObjectError("password","El password no coincide");
             result.addError(errorPass);
         }
          if (result.hasErrors()){
              map.put("usuario",u);

            map.put("error",result.getAllErrors().stream().map(this::mapError).collect(Collectors.toList()));
           //   map.put("error",result.getFieldErrors().stream().map(this::mapError).collect(Collectors.toList()));
          }
          else{
          String hash=  passwordEncoder().encode(u.getContrasenna());
          u.setContrasenna(hash);
         map.put("usuario",usuarios.save(u));
         map.put("mesagge","Usuario Insertado");}
        return new ResponseEntity<Map<String,Object>>( map,HttpStatus.OK);

    }

       //@GetMapping(value = "/eliminar/{id}")
       @DeleteMapping(value = "/eliminar/{id}")
      public ResponseEntity<Map<String, Object>> EliminarUsuario(@PathVariable(name = "id") int id)  {
       HashMap<String, Object> map =new HashMap<>();

        try {
            Usuario user = usuarioService.Eliminar(id);
            map.put("message","Usuario  "+"  "+ user.getNombre()+"  "+ " eliminado correctamete");
        } catch (Exception e) {
            map.put("messageError", e.getMessage());
        }

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }
   @PostMapping(value = "/editarUsuario/cambiarpass")
    public ResponseEntity<Map<String,Object>>CambiarPassword(@Valid @RequestBody ChangePasswordDTO changeDTO, BindingResult error)   {
      HashMap<String,Object> map = new HashMap<>();
       if(error.hasErrors()){
                 map.put("usuario",changeDTO);

                 map.put("error",error.getAllErrors().stream().map(this::mapError).collect(Collectors.toList()));
             }
       else{
           try {
            Usuario u =   usuarioService.Cambiarpassword(changeDTO);
            map.put("usuario",u);
            map.put("message", "LA contrasenna se actualizo correctamente");
           } catch (Exception e) {
               map.put("changeDTO",changeDTO);
               map.put("error",e.getMessage());
           }


       }
          return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);


    }










     private boolean VerificarUsuario(String usuario){
         List<Usuario> nameuser = usuarios.findAll();
         for (Usuario user: nameuser) {
             if(user.getUsuario().equals(usuario))
                 return true;
         }
           return false;

     }

     public Usuario getUsuarioById(int id) throws Exception {
        Usuario userfound = usuarios.findById(id);
       if(userfound != null)
           return userfound;
              throw new Exception("El usuario no existe");
     }


     private boolean CheckUserName(Usuario user) {
    Optional<Usuario> userfound = usuarios.findByUsuario(user.getUsuario());
    if(userfound.isPresent()){
      return true;
    }
      return false;
     }

      private boolean ChekearPasswordMatch(Usuario user) {
       if(!user.getContrasenna().equals(user.getConfirmarContrasenna())){
         return true;
       }
       return false;
      }



     private ValidatorError mapError(ObjectError objectError) {
         if (objectError instanceof FieldError) {
             return new ValidatorError(((FieldError) objectError).getField(),
                     objectError.getDefaultMessage());
         }
         return new ValidatorError(objectError.getObjectName(), objectError.getDefaultMessage());
     }

    private PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;

    }


}
