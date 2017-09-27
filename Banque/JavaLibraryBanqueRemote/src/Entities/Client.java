/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adrie
 */
@Entity
@Table(name = "CLIENT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByLogin", query = "SELECT c FROM Client c WHERE c.login = :login"),
    @NamedQuery(name = "Client.findByNom", query = "SELECT c FROM Client c WHERE c.nom = :nom"),
    @NamedQuery(name = "Client.findByPrenom", query = "SELECT c FROM Client c WHERE c.prenom = :prenom")
})
public class Client implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 50)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 50)
    @Column(name = "PRENOM")
    private String prenom;
    @OneToMany(mappedBy = "refclient")
    private Collection<Credits> creditsCollection;
    @OneToMany(mappedBy = "refclient")
    private Collection<Compte> compteCollection;

    public Client()
    {
    }

    public Client(String login)
    {
        this.login = login;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    @XmlTransient
    public Collection<Credits> getCreditsCollection()
    {
        return creditsCollection;
    }

    public void setCreditsCollection(Collection<Credits> creditsCollection)
    {
        this.creditsCollection = creditsCollection;
    }

    @XmlTransient
    public Collection<Compte> getCompteCollection()
    {
        return compteCollection;
    }

    public void setCompteCollection(Collection<Compte> compteCollection)
    {
        this.compteCollection = compteCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client))
        {
            return false;
        }
        Client other = (Client) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return nom + " " + prenom;
    }
    
}
