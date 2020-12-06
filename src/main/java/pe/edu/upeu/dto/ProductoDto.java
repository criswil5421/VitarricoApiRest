package pe.edu.upeu.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductoDto {

    @NotBlank
    private String nombre;
    @Min(0)
    private double precio;

    public ProductoDto(@NotBlank String nombre, @Min(0) double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

}
