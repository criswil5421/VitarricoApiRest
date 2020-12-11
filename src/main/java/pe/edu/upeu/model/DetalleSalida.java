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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Cris
 */
@Entity
@Data
@Table(name = "detalle_salida")

public class DetalleSalida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_id")
    private Integer detalleId;
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne(optional = false)
    private Producto1 productoId;
    @JoinColumn(name = "salida_id", referencedColumnName = "salida_id")
    @ManyToOne(optional = false)
    private Salida salidaId;


}
