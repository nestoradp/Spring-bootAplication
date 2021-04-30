package cu.curso.estudio.Entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "El Primer Nombre no puede estar vacio")
    @Size(min = 5, max = 8, message = "Caracteres incorectos")
    private String PrimerNombre;

    @Column
    @NotBlank(message = "El Segundo Nombreno puede estar vacio")
    @Size(min = 5, max = 8, message = "Caracteres incorectos")
    private String SegundoNombre;

    @Column
    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "Formato incorrecto de correo")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "El usuario no puede estar vacio")
    private String usuario;

    @Column
    @NotBlank(message = "la contrasenna no puede estar vacio")
    private String contrasenna;

    @Transient
    private String confirmarContrasenna;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Rol> roles;

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

    public Set<Rol> getRoles() {
        return roles;
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

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
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
