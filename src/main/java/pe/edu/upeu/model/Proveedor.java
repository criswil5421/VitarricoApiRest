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
@Table(name = "proveedor")

public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedor_id")
    private Integer proveedorId;
    @Basic(optional = false)
    @Column(name = "proveedor_nombre")
    private String proveedorNombre;
    @Basic(optional = false)
    @Column(name = "proveedor_direccion")
    private String proveedorDireccion;
    @Basic(optional = false)
    @Column(name = "proveedor_correo")
    private String proveedorCorreo;
    @Basic(optional = false)
    @Column(name = "proveedor_telefono")
    private String proveedorTelefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorId")
    @JsonIgnore
    private Collection<Pedido> pedidoCollection;


    
}
