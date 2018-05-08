/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Employe;
import services.Service;
/**
 *
 * @author Emilie Borghino
 */
class ActionConnexionEmploye {

    void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employe e = Service.connexionEmploye(Integer.parseInt(request.getParameter("login")));
        if(e != null){ 
            session.setAttribute("employe", e);
            request.setAttribute("isConnected", 1); //true
        }
        else
             request.setAttribute("isConnected", 0); //false
    }
    
}
