/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Incident;
import modele.Intervention;
import services.Service;

/**
 *
 * @author Emilie Borghino
 */
class ActionDemandeIncident {

    void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Intervention intervention = new Incident((Client) session.getAttribute("client"), request.getParameter("description"));
        request.setAttribute("isSend", Service.envoyerIntervention(intervention));
    }
    
}
