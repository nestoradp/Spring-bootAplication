package cu.curso.estudio.service;


import cu.curso.estudio.DTO.ChangePasswordDTO;
import cu.curso.estudio.Entity.Usuario;
import cu.curso.estudio.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public Iterable<Usuario> DevolverUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario Eliminar(int id) throws Exception{
      Usuario user = getUsuarioById(id);
          usuarioRepository.delete(user);
          return user;
    }

    @Override
    public Usuario Cambiarpassword(ChangePasswordDTO changeDTO) throws Exception {
         Usuario user = getUsuarioById(changeDTO.getId());
       if(! passwordEncoder().matches(changeDTO.getAnteriorContrasenna(),user.getContrasenna())){
           throw new Exception("La contrasenna Anterior es incorrecta");

       }

       if(passwordEncoder().matches(user.getContrasenna(),changeDTO.getAnteriorContrasenna())){
           throw new Exception("La contrasenna no puede ser igual a la anterior");
       }

       if(!changeDTO.getNewContrasenna().equals(changeDTO.getConfirmarContrasenna())){
           throw new Exception("La contrasenna no coinciden");
    }
          user.setContrasenna( passwordEncoder().encode(changeDTO.getNewContrasenna()));
        return usuarioRepository.save(user);



    }

    @Override
    public Usuario getUsuarioById(int id) throws Exception {
        Usuario userfound = usuarioRepository.findById(id);
        if(userfound != null)
            return userfound;
        throw new Exception("El usuario no existe");
    }

    private PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;

    }


    }



