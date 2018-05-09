/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modele.Employe;
import modele.Intervention;
import services.Service;

/**
 *
 * @author Emilie Borghino
 */
class ActionCarteIntervention {

    void execute(HttpServletRequest request) {
        List<Intervention> i = Service.rechercheInterventionDuJour();
        request.setAttribute("interventions", i);
        System.out.println(request.getAttribute("interventions"));
    }
    
}
