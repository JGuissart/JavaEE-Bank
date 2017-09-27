/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import Entities.Logs;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/myTopicBanque"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myTopicBanque"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/myTopicBanque"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MessageBeanClient implements MessageListener
{
    @PersistenceContext(unitName = "EnterpriseApplicationBanque-ejbPU")
    private EntityManager em;
    
    public MessageBeanClient()
    {
    }
    
    @Override
    public void onMessage(Message message)
    {   
        Logs log = null;
        
        try
        {
            TextMessage tm = (TextMessage)message;
            if(!tm.getStringProperty("type").equals("validation"))
            {
                log = new Logs(tm.getText());
                em.persist(log);
            }
            
        }
        catch(JMSException ex)
        {
            System.out.println("Une erreur est survenue !");
        }  
    }

    public void persist(Object object)
    {
        em.persist(object);
    }
    
}
