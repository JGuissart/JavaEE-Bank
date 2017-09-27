/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adrie
 */
@Entity
@Table(name = "LOGS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findByIdlogs", query = "SELECT l FROM Logs l WHERE l.idlogs = :idlogs"),
    @NamedQuery(name = "Logs.findByInfos", query = "SELECT l FROM Logs l WHERE l.infos = :infos"),
    @NamedQuery(name = "Logs.findByDateheure", query = "SELECT l FROM Logs l WHERE l.dateheure = :dateheure")
})
public class Logs implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "IDLOGS")
    private String idlogs;
    @Size(max = 500)
    @Column(name = "INFOS")
    private String infos;
    @Column(name = "DATEHEURE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateheure;

    public Logs()
    {
    }

    public Logs(String message)
    {
        this.infos = message;
        this.dateheure = new Date();
    }
    
    public String getIdlogs()
    {
        return idlogs;
    }

    public void setIdlogs(String idlogs)
    {
        this.idlogs = idlogs;
    }

    public String getInfos()
    {
        return infos;
    }

    public void setInfos(String infos)
    {
        this.infos = infos;
    }

    public Date getDateheure()
    {
        return dateheure;
    }

    public void setDateheure(Date dateheure)
    {
        this.dateheure = dateheure;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idlogs != null ? idlogs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs))
        {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.idlogs == null && other.idlogs != null) || (this.idlogs != null && !this.idlogs.equals(other.idlogs)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Entities.Logs[ idlogs=" + idlogs + " ]";
    }
    
}
