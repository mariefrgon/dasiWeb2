/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Intervention;
import modele.Livraison;
import services.Service;

/**
 *
 * @author Emilie Borghino
 */
class ActionDemandeLivraison {

    void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Intervention intervention = new Livraison(request.getParameter("objet"), request.getParameter("entreprise"), (Client) session.getAttribute("client"), request.getParameter("description"));
        request.setAttribute("isSend", Service.envoyerIntervention(intervention));
    }
    
}
