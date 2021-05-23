package cu.curso.estudio.service;

import cu.curso.estudio.Entity.Rol;
import cu.curso.estudio.Entity.Usuario;
import cu.curso.estudio.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username).get();
            if(usuario==null){
                throw new UsernameNotFoundException("El usuario no existe");
            }

        List<GrantedAuthority> authorities = new ArrayList<>();
            for(Rol rol : usuario.getRoles())
              authorities.add(new SimpleGrantedAuthority(rol.getNombre()));

        User user = new User(usuario.getNombre(),usuario.getContrasenna(),authorities);

           return user;



    }
}
