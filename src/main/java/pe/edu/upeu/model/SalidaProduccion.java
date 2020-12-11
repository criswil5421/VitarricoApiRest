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
@Table(name = "salida_produccion")

public class SalidaProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salpro_id")
    private Integer salproId;
    @Basic(optional = false)
    @Column(name = "salpro_fecha")
    @Temporal(TemporalType.DATE)
    private Date salproFecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salproId")
    @JsonIgnore
    private Collection<DetalleProduccion> detalleProduccionCollection;
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne(optional = false)
    private Producto1 productoId;


    
}
