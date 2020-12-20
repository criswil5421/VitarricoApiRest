/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Cris
 */
@Entity
@Data
@Table(name = "producto1")

public class Producto1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Integer productoId;
    @Basic(optional = false)
    @Column(name = "producto_nombre")
    private String productoNombre;
    @Basic(optional = false)
    @Column(name = "producto_precio")
    private Double productoPrecio;
    @Basic(optional = false)
    @Column(name = "producto_ingreso")
    private String productoIngreso;
    @Basic(optional = false)
    @Column(name = "producto_cantidad")
    private String productoCantidad;
    @Basic(optional = false)
    @Column(name = "producto_descripcion")
    private String productoDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
    @JsonIgnore
    private Collection<DetalleSalida> detalleSalidaCollection;
    @JoinColumn(name = "almacen_id", referencedColumnName = "almacen_id")
    @ManyToOne(optional = false)
    private Almacen almacenId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
    @JsonIgnore
    private Collection<SalidaProduccion> salidaProduccionCollection;

    
}
