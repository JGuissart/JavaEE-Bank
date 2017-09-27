/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adrie
 */
@Entity
@Table(name = "CREDITS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Credits.findAll", query = "SELECT c FROM Credits c"),
    @NamedQuery(name = "Credits.findByIdcredit", query = "SELECT c FROM Credits c WHERE c.idcredit = :idcredit"),
    @NamedQuery(name = "Credits.findByMontant", query = "SELECT c FROM Credits c WHERE c.montant = :montant"),
    @NamedQuery(name = "Credits.findByTaux", query = "SELECT c FROM Credits c WHERE c.taux = :taux"),
    @NamedQuery(name = "Credits.findByDuree", query = "SELECT c FROM Credits c WHERE c.duree = :duree"),
    @NamedQuery(name = "Credits.findBySalaire", query = "SELECT c FROM Credits c WHERE c.salaire = :salaire"),
    @NamedQuery(name = "Credits.findByChargecredit", query = "SELECT c FROM Credits c WHERE c.chargecredit = :chargecredit"),
    @NamedQuery(name = "Credits.findByAccorde", query = "SELECT c FROM Credits c WHERE c.accorde = :accorde")
})
public class Credits implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "IDCREDIT")
    private String idcredit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTANT")
    private Double montant;
    @Column(name = "TAUX")
    private Double taux;
    @Column(name = "DUREE")
    private Integer duree;
    @Column(name = "SALAIRE")
    private Double salaire;
    @Column(name = "CHARGECREDIT")
    private Double chargecredit;
    @Column(name = "ACCORDE")
    private Boolean accorde;
    @JoinColumn(name = "REFCLIENT", referencedColumnName = "LOGIN")
    @ManyToOne
    private Client refclient;

    public Credits()
    {
    }

    public Credits(float m, float t, int d, float s, float c, boolean a, Client cli)
    {
        this.montant = new Double(m);
        this.taux = new Double(t);
        this.duree = d;
        this.salaire =new Double(s);
        this.chargecredit=new Double(c);
        this.accorde = a;
        this.refclient = cli;
    }

    public String getIdcredit()
    {
        return idcredit;
    }

    public void setIdcredit(String idcredit)
    {
        this.idcredit = idcredit;
    }

    public Double getMontant()
    {
        return montant;
    }

    public void setMontant(Double montant)
    {
        this.montant = montant;
    }

    public Double getTaux()
    {
        return taux;
    }

    public void setTaux(Double taux)
    {
        this.taux = taux;
    }

    public Integer getDuree()
    {
        return duree;
    }

    public void setDuree(Integer duree)
    {
        this.duree = duree;
    }

    public Double getSalaire()
    {
        return salaire;
    }

    public void setSalaire(Double salaire)
    {
        this.salaire = salaire;
    }

    public Double getChargecredit()
    {
        return chargecredit;
    }

    public void setChargecredit(Double chargecredit)
    {
        this.chargecredit = chargecredit;
    }

    public Boolean getAccorde()
    {
        return accorde;
    }

    public void setAccorde(Boolean accorde)
    {
        this.accorde = accorde;
    }

    public Client getRefclient()
    {
        return refclient;
    }

    public void setRefclient(Client refclient)
    {
        this.refclient = refclient;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idcredit != null ? idcredit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credits))
        {
            return false;
        }
        Credits other = (Credits) object;
        if ((this.idcredit == null && other.idcredit != null) || (this.idcredit != null && !this.idcredit.equals(other.idcredit)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Entities.Credits[ idcredit=" + idcredit + " ]";
    }
    
}
