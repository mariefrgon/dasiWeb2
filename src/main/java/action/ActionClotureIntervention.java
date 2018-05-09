/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import modele.Employe;
import modele.Intervention;
import services.Service;

/**
 *
 * @author Emilie Borghino
 */
class ActionClotureIntervention {

    void execute(HttpServletRequest request) {
        Employe e = (Employe) request.getSession().getAttribute("employe");
        boolean probleme = false;
        if(request.getParameter("probleme").equals("true"))
            probleme = true;
        request.setAttribute("isCloture", Service.cloturerIntervention((Intervention) request.getSession().getAttribute("intervention"), probleme, request.getParameter("commentaire")));
    }
    
}
