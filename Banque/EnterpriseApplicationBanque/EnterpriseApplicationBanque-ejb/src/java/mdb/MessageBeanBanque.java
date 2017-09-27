/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import Entities.Client;
import Entities.Credits;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adrie
 */
@MessageDriven(activationConfig =
{
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/myTopicBanque2"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myTopicBanque"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/myTopicBanque"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MessageBeanBanque implements MessageListener
{
    @PersistenceContext(unitName = "EnterpriseApplicationBanque-ejbPU")
    private EntityManager em;
    
    public MessageBeanBanque()
    {
    }
    
    @Override
    public void onMessage(Message message)
    {
        TextMessage tm = (TextMessage)message;
        
        try
        {
            if(tm.getStringProperty("type").compareTo("demande")==0 && tm.getBooleanProperty("acceptation"))
            {
                boolean acception = tm.getBooleanProperty("acceptation");
                float montant = tm.getFloatProperty("montant");
                float taux = tm.getFloatProperty("taux");
                int duree = tm.getIntProperty("duree");
                float salaire = tm.getFloatProperty("salaire");
                float charge = tm.getFloatProperty("charge");
                Client client = (Client)em.createNamedQuery("Client.findByLogin").setParameter("login", tm.getStringProperty("client")).getSingleResult();
            
                Credits credit = new Credits(montant, taux, duree, salaire, charge, acception, client);
            
                persist(credit);
            }
            

        }
        catch(JMSException ex)
        {
            System.out.println("JMS exception :" + ex);
        }
    }

    public void persist(Object object)
    {
        em.persist(object);
    }
    
}
