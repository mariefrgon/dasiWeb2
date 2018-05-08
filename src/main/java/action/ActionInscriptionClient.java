/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.proactifb3328.util.GeoTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import modele.Client;
import services.Service;

/**
 *
 * @author eborghino
 */
class ActionInscriptionClient {
    public void execute(HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date d = null;
        try {
            d = sdf.parse(request.getParameter("datenaissance"));
        } catch (ParseException ex) {
            Logger.getLogger(ActionInscriptionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(GeoTest.getLatLng((request.getParameter("adresse")+ " " + request.getParameter("cp") + " " + request.getParameter("ville"))) == null){
            request.setAttribute("isRegistered", -1);
        }else{
            Client c = new Client(
                    request.getParameter("civilite"), 
                    request.getParameter("prenom"),
                    request.getParameter("nom"),
                    d, 
                    request.getParameter("adresse")+ " " + request.getParameter("cp") + " " + request.getParameter("ville"),
                    request.getParameter("tel"),
                    request.getParameter("email"));
            request.setAttribute("isRegistered", Service.inscriptionClient(c));
        }
    }
}
