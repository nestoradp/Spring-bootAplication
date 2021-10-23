package cu.curso.estudio.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ChangePasswordDTO {

    @NotNull
    private int id;


    @NotBlank(message = "El campo no puede estar vacio")
    private String anteriorContrasenna;


    @NotBlank(message = "El campo no puede estar vacio")
    private String newContrasenna;


    @NotBlank(message = "El campo no puede estar vacio")
    private String confirmarContrasenna;

    public int getId() {
        return id;
    }

    public String getAnteriorContrasenna() {
        return anteriorContrasenna;
    }

    public String getNewContrasenna() {
        return newContrasenna;
    }

    public String getConfirmarContrasenna() {
        return confirmarContrasenna;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnteriorContrasenna(String anteriorContrasenna) {
        this.anteriorContrasenna = anteriorContrasenna;
    }

    public void setNewContrasenna(String newContrasenna) {
        this.newContrasenna = newContrasenna;
    }

    public void setConfirmarContrasenna(String confirmarContrasenna) {
        this.confirmarContrasenna = confirmarContrasenna;
    }
}
