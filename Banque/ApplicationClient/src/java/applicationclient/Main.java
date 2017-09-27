/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationclient;

import Ejb.SessionBeanClientRemote;
import gui.ChoixApplicGUI;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author adrie
 */
public class Main {

    @Resource(mappedName = "jms/myTopicBanque")
    private static Topic myTopicBanque;
    @Resource(mappedName = "jms/myTopicFactoryBanque")
    private static ConnectionFactory myTopicBanqueFactory;

    private static Connection connection = null;
    private static Session session = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {    
        try
        {
            connection = myTopicBanqueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
        }
        catch(JMSException ex)
        {
            System.out.println("Main error : " + ex);
        }
                
        ChoixApplicGUI choix = new ChoixApplicGUI(myTopicBanque, session);
        choix.setVisible(true);
    }
    
}
