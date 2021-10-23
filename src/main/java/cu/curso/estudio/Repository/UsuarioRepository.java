package cu.curso.estudio.Repository;

import cu.curso.estudio.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

       public Optional<Usuario> findByUsuario(String usuario);

       public Usuario findById(int id);




}
