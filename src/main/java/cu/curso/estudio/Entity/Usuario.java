package cu.curso.estudio.Entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.management.relation.Role;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native" )
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column
    private String PrimerNombre;

    @Column
    private String SegundoNombre;

    @Column
    private String email;

    @Column(unique = true)
    private String usuario;

    @Column
    private String contrasenna;

    @Transient
    private String confirmarContrasenna;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Rol> roles;

    public Usuario() {

        super();
    }

    public Usuario(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPrimerNombre() {
        return PrimerNombre;
    }

    public String getSegundoNombre() {
        return SegundoNombre;
    }

    public String getEmail() {
        return email;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }





    public void setPrimerNombre(String primerNombre) {
        PrimerNombre = primerNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        SegundoNombre = segundoNombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", PrimerNombre='" + PrimerNombre + '\'' +
                ", SegundoNombre='" + SegundoNombre + '\'' +
                ", email='" + email + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contrasenna='" + contrasenna + '\'' +
                ", confirmarContrasenna='" + confirmarContrasenna + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return id == usuario1.id && Objects.equals(PrimerNombre, usuario1.PrimerNombre) && Objects.equals(SegundoNombre, usuario1.SegundoNombre) && Objects.equals(email, usuario1.email) && Objects.equals(usuario, usuario1.usuario) && Objects.equals(contrasenna, usuario1.contrasenna) && Objects.equals(confirmarContrasenna, usuario1.confirmarContrasenna) && Objects.equals(roles, usuario1.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, PrimerNombre, SegundoNombre, email, usuario, contrasenna, confirmarContrasenna, roles);
    }
}
