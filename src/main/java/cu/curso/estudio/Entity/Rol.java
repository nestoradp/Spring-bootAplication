package cu.curso.estudio.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native" )
    @GenericGenerator(name = "native", strategy = "native")
   private int id;


    @Column
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @Column
    @Size(max=20, message = "Limite de caracteres")
    @NotBlank(message = "La descricpcion no puede estar vacia")
    private String descripcion;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
