package pe.edu.upeu.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Cris
 */

@Entity
@Data
@Table(name = "almacen")
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "almacen_id")
    private Integer almacenId;
    @Basic(optional = false)
    @Column(name = "almacen_nombre")
    private String almacenNombre;
    @Basic(optional = false)
    @Column(name = "almacen_direccion")
    private String almacenDireccion;
    @Basic(optional = false)
    @Column(name = "almacen_telefono")
    private String almacenTelefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacenId")
    @JsonIgnore
    private Collection<MateriaPrima> materiaPrimaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacenId")
    @JsonIgnore
    private Collection<Producto1> productoCollection;

}
