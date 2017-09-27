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
@Table(name = "COMPTE")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByIdcompte", query = "SELECT c FROM Compte c WHERE c.idcompte = :idcompte"),
    @NamedQuery(name = "Compte.findBySolde", query = "SELECT c FROM Compte c WHERE c.solde = :solde"),
    @NamedQuery(name = "Compte.ajoutSolde", query = "UPDATE Compte c SET c.solde = c.solde + :montant WHERE c.idcompte = :idcompte"),
    @NamedQuery(name = "Compte.retraitSolde", query = "UPDATE Compte c SET c.solde = c.solde - :montant WHERE c.idcompte = :idcompte"),
    @NamedQuery(name = "Compte.findByRefclient", query = "SELECT c FROM Compte c WHERE c.refclient = :refclient")
})
public class Compte implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDCOMPTE")
    private String idcompte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SOLDE")
    private Double solde;
    @JoinColumn(name = "REFCLIENT", referencedColumnName = "LOGIN")
    @ManyToOne
    private Client refclient;

    public Compte()
    {
    }
    
    public Compte(String idcompte)
    {
        this.idcompte = idcompte;
    }

    public String getIdcompte()
    {
        return idcompte;
    }

    public void setIdcompte(String idcompte)
    {
        this.idcompte = idcompte;
    }

    public Double getSolde()
    {
        return solde;
    }

    public void setSolde(Double solde)
    {
        this.solde = solde;
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
        hash += (idcompte != null ? idcompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte))
        {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.idcompte == null && other.idcompte != null) || (this.idcompte != null && !this.idcompte.equals(other.idcompte)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return idcompte + "/" + solde;
    }
    
}
