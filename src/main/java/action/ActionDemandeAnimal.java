/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Animal;
import modele.Client;
import modele.Intervention;
import services.Service;

/**
 *
 * @author Emilie Borghino
 */
class ActionDemandeAnimal {

    void execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        Intervention intervention = new Animal(request.getParameter("type"), (Client) session.getAttribute("client"), request.getParameter("description"));
        request.setAttribute("isSend", Service.envoyerIntervention(intervention));
    }
    
}
