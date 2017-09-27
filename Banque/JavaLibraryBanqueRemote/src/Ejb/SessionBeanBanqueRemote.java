/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entities.Client;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author adrie
 */
@Remote
public interface SessionBeanBanqueRemote {

    void LoginEmploye();

    void LoginAdmin();

    int demandeCredit(float montant, float taux, int duree, float salaireNet, float charge, Client client); 

    List getClients();

    Client getClient(String login);

    String getEmployeLogin();
    
}
