/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;


import Entities.Client;
import Entities.Compte;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author adrie
 */
@Stateless
@DeclareRoles("client")
public class SessionBeanClient implements SessionBeanClientRemote 
{
    @PersistenceContext(unitName = "EnterpriseApplicationBanque-ejbPU")
    private EntityManager em;
    @Resource SessionContext ctx;

    @Override
    @RolesAllowed("client")
    public Client LoginClient() 
    {
        String login = ctx.getCallerPrincipal().getName();
        Client client = em.find(Client.class, login);
  
        return client;
    }
    
    @Override
    @RolesAllowed("client")
    public List<Compte> getComptesClient(Client client)
    {  
        List<Compte> listeComptes = em.createNamedQuery("Compte.findByRefclient").setParameter("refclient", client).getResultList();
        
        return listeComptes;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object)
    {
        em.persist(object);
    }

    @Override
    @RolesAllowed("client")
    public boolean transfert(Compte source, String destination, float montant)
    {
        try
        {
            Compte destinataire = (Compte) em.createNamedQuery("Compte.findByIdcompte").setParameter("idcompte", destination).getSingleResult();
            Query q = em.createNamedQuery("Compte.retraitSolde");
            q.setParameter("montant", montant);
            q.setParameter("idcompte", source.getIdcompte());
            q.executeUpdate();

            q = em.createNamedQuery("Compte.ajoutSolde");
            q.setParameter("montant", montant);
            q.setParameter("idcompte", destination);
            q.executeUpdate();

            
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }


}
