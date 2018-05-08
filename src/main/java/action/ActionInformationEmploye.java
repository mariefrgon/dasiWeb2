/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Employe;
import modele.Intervention;
import services.Service;

/**
 *
 * @author Emilie Borghino
 */
class ActionInformationEmploye {

    void execute(HttpServletRequest request) {
        Employe e = (Employe) request.getSession().getAttribute("employe");
        Intervention i = Service.rechercheInterventionEnCoursEmploye(e);
        request.setAttribute("intervention", i);
    }
    
}
