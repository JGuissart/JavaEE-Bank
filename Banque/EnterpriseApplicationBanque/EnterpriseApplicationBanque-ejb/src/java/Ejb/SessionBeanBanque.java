/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entities.Client;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adrie
 */
@Stateless
@DeclareRoles({"employe","admin"})
public class SessionBeanBanque implements SessionBeanBanqueRemote {
    @Resource(mappedName = "jms/myTopicBanque")
    private Topic myTopicBanque;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    @PersistenceContext(unitName = "EnterpriseApplicationBanque-ejbPU")
    private EntityManager em;
    @Resource SessionContext ctx;

    @Override
    @RolesAllowed("employe")
    public void LoginEmploye() {
    }
    
    @Override
    @RolesAllowed("employe")
    public int demandeCredit(float montant, float taux, int duree, float salaireNet, float charge, Client client)
    {
        try
        {
            if(montant < 250000 && charge<40*salaireNet/100)
            {
                sendJMSMessageToMyTopicBanque("Crédit accepté d'un montant de " + montant + " avec un taux de " + taux + " pour le client " + client.getLogin(),true, montant, taux, duree, salaireNet, charge, client);
                
                return 1;
            }
            else
            {
                sendJMSMessageToMyTopicBanque("Crédit en attente d'acceptation d'un montant de " + montant + " avec un taux de " + taux + " pour le client " + client.getLogin(),false,montant,taux,duree,salaireNet,charge,client);
                
                return -1;
            }
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    @Override
    @RolesAllowed("admin")
    public void LoginAdmin() {
    }

    @Override
    public List getClients()
    {
        return em.createNamedQuery("Client.findAll").getResultList();
    }
    
    

    public void persist(Object object)
    {
        em.persist(object);
    }

    private void sendJMSMessageToMyTopicBanque(String messageData, boolean acceptation, float montant, float taux, int duree, float salaireNet, float charge, Client client)
    {   
        try
        {
            TextMessage tm = context.createTextMessage();
        
            tm.setText(messageData);
            
            tm.setStringProperty("type", "demande");
            tm.setBooleanProperty("acceptation", acceptation);
            tm.setFloatProperty("montant", montant);
            tm.setFloatProperty("taux", taux);
            tm.setIntProperty("duree", duree);
            tm.setFloatProperty("salaire", salaireNet);
            tm.setFloatProperty("charge", charge);
            tm.setStringProperty("client", client.getLogin());
            tm.setStringProperty("login", ctx.getCallerPrincipal().getName());
            
            context.createProducer().send(myTopicBanque, tm);
            
        }
        catch(JMSException ex)
        {
            System.out.println("JMS Exception : " + ex);
        }
    }

    @Override
    public Client getClient(String login)
    {
        return (Client)em.createNamedQuery("Client.findByLogin").setParameter("login", login).getSingleResult();
    }

    @Override
    public String getEmployeLogin()
    {
        return ctx.getCallerPrincipal().getName();
    }
}
