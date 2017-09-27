/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Ejb.SessionBeanBanqueRemote;
import Entities.Client;
import Entities.Credits;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author adrie
 */
public class CreditEnAttente
{
    private Credits credit = null;
    
    private String login = null;
    
    public CreditEnAttente(TextMessage tm)
    {
        try
        {    
            boolean acception = tm.getBooleanProperty("acceptation");
            float montant = tm.getFloatProperty("montant");
            float taux = tm.getFloatProperty("taux");
            int duree = tm.getIntProperty("duree");
            float salaire = tm.getFloatProperty("salaire");
            float charge = tm.getFloatProperty("charge");
            Client client = lookupSessionBeanBanqueRemote().getClient(tm.getStringProperty("client"));

            credit = new Credits(montant, taux, duree, salaire, charge, acception, client);
            login = tm.getStringProperty("login");
        }
        catch(Exception ex)
        {
            System.out.println("Error :" + ex);
        }
    }
    
    public Credits getCredit()
    {
        return credit;
    }
    
    public String getLogin()
    {
        return login;
    }
    
    @Override
    public String toString()
    {
        return "Montant : " + credit.getMontant() + " et Taux : " + credit.getTaux();
    }

    private SessionBeanBanqueRemote lookupSessionBeanBanqueRemote()
    {
        try
        {
            Context c = new InitialContext();
            return (SessionBeanBanqueRemote) c.lookup("java:comp/env/SessionBeanBanque");
        } catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
