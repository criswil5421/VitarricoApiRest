package pe.edu.upeu.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic(optional = false)
    @NotNull

    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull

    @Column(name = "precio")
    private double precio;


    /*public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }*/

}
