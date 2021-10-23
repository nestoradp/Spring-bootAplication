package cu.curso.estudio.Repository;

import cu.curso.estudio.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepositori extends JpaRepository<Rol,Integer> {

}
