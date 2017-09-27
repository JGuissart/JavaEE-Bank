/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;


import Entities.Client;
import Entities.Compte;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author adrie
 */
@Remote
public interface SessionBeanClientRemote {

    Client LoginClient();

    List<Compte> getComptesClient(Client client);

    boolean transfert(Compte source, String destination, float montant);
    
}
