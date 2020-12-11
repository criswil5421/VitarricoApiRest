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
@Table(name = "tienda")

public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tienda_id")
    private Integer tiendaId;
    @Basic(optional = false)
    @Column(name = "tienda_nombre")
    private String tiendaNombre;
    @Basic(optional = false)
    @Column(name = "tienda_direccion")
    private String tiendaDireccion;
    @Basic(optional = false)
    @Column(name = "tienda_telefono")
    private String tiendaTelefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiendaId")
    @JsonIgnore
    private Collection<Salida> salidaCollection;


}
