package cu.curso.estudio.service;

import cu.curso.estudio.DTO.ChangePasswordDTO;
import cu.curso.estudio.Entity.Usuario;

public interface IUsuarioService {

    public Iterable<Usuario>DevolverUsuarios();


    public Usuario Eliminar(int id) throws Exception;

    public Usuario Cambiarpassword(ChangePasswordDTO changeDTO) throws Exception;

    public Usuario getUsuarioById(int id) throws Exception;


}
