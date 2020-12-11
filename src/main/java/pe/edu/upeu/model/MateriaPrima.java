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
@Table(name = "materia_prima")

public class MateriaPrima implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "materia_id")
    private Integer materiaId;
    @Basic(optional = false)
    @Column(name = "materia_nombre")
    private String materiaNombre;
    @Basic(optional = false)
    @Column(name = "materia_cantidad")
    private String materiaCantidad;
    @Basic(optional = false)
    @Column(name = "materia_ingreso")
    @Temporal(TemporalType.DATE)
    private Date materiaIngreso;
    @JoinColumn(name = "almacen_id", referencedColumnName = "almacen_id")
    @ManyToOne(optional = false)
    private Almacen almacenId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaId")
    @JsonIgnore
    private Collection<DetalleEntrada> detalleEntradaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaId")
    @JsonIgnore
    private Collection<DetalleProduccion> detalleProduccionCollection;

    
}
