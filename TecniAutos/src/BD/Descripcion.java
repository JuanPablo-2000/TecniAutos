/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Pablo
 */
@Entity
@Table(name = "descripcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descripcion.findAll", query = "SELECT d FROM Descripcion d"),
    @NamedQuery(name = "Descripcion.findByRepuesto", query = "SELECT d FROM Descripcion d WHERE d.repuesto = :repuesto"),
    @NamedQuery(name = "Descripcion.findByIdrepuesto", query = "SELECT d FROM Descripcion d WHERE d.idrepuesto = :idrepuesto"),
    @NamedQuery(name = "Descripcion.findByValor", query = "SELECT d FROM Descripcion d WHERE d.valor = :valor"),
    @NamedQuery(name = "Descripcion.findByCantidad", query = "SELECT d FROM Descripcion d WHERE d.cantidad = :cantidad")})
public class Descripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "repuesto")
    private String repuesto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrepuesto")
    private Integer idrepuesto;
    @Column(name = "valor")
    private BigInteger valor;
    @Column(name = "cantidad")
    private String cantidad;
    @JoinColumn(name = "idregistro", referencedColumnName = "idregistro")
    @ManyToOne(optional = false)
    private Registro idregistro;

    public Descripcion() {
    }

    public Descripcion(Integer idrepuesto) {
        this.idrepuesto = idrepuesto;
    }

    public String getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(String repuesto) {
        this.repuesto = repuesto;
    }

    public Integer getIdrepuesto() {
        return idrepuesto;
    }

    public void setIdrepuesto(Integer idrepuesto) {
        this.idrepuesto = idrepuesto;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Registro getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Registro idregistro) {
        this.idregistro = idregistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrepuesto != null ? idrepuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Descripcion)) {
            return false;
        }
        Descripcion other = (Descripcion) object;
        if ((this.idrepuesto == null && other.idrepuesto != null) || (this.idrepuesto != null && !this.idrepuesto.equals(other.idrepuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Descripcion[ idrepuesto=" + idrepuesto + " ]";
    }
    
}
