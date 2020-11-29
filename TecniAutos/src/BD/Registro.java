/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Familia-Botero
 */
@Entity
@Table(name = "registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r")
    , @NamedQuery(name = "Registro.findByIdregistro", query = "SELECT r FROM Registro r WHERE r.idregistro = :idregistro")
    , @NamedQuery(name = "Registro.findByMarca", query = "SELECT r FROM Registro r WHERE r.marca = :marca")
    , @NamedQuery(name = "Registro.findByPlaca", query = "SELECT r FROM Registro r WHERE r.placa = :placa")
    , @NamedQuery(name = "Registro.findByTecnico", query = "SELECT r FROM Registro r WHERE r.tecnico = :tecnico")
    , @NamedQuery(name = "Registro.findByCosto", query = "SELECT r FROM Registro r WHERE r.costo = :costo")
    , @NamedQuery(name = "Registro.findByFecha", query = "SELECT r FROM Registro r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "Registro.findByCliente", query = "SELECT r FROM Registro r WHERE r.cliente = :cliente")
    , @NamedQuery(name = "Registro.findByCedula", query = "SELECT r FROM Registro r WHERE r.cedula = :cedula")
    , @NamedQuery(name = "Registro.findByKilometraje", query = "SELECT r FROM Registro r WHERE r.kilometraje = :kilometraje")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idregistro")
    private Integer idregistro;
    @Column(name = "marca")
    private String marca;
    @Column(name = "placa")
    private String placa;
    @Column(name = "tecnico")
    private String tecnico;
    @Column(name = "costo")
    private BigInteger costo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "kilometraje")
    private String kilometraje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idregistro")
    private List<Descripcion> descripcionList;

    public Registro() {
    }

    public Registro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public Integer getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public BigInteger getCosto() {
        return costo;
    }

    public void setCosto(BigInteger costo) {
        this.costo = costo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    @XmlTransient
    public List<Descripcion> getDescripcionList() {
        return descripcionList;
    }

    public void setDescripcionList(List<Descripcion> descripcionList) {
        this.descripcionList = descripcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregistro != null ? idregistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.idregistro == null && other.idregistro != null) || (this.idregistro != null && !this.idregistro.equals(other.idregistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Registro[ idregistro=" + idregistro + " ]";
    }
    
}
