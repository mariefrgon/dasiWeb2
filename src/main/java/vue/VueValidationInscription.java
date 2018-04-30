/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import action.ActionServlet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Animal;
import modele.Client;
import modele.Intervention;
import modele.Livraison;
import services.Service;

/**
 *
 * @author eborghino
 */
public class VueValidationInscription {

    public void validationInscription(HttpServletRequest request, HttpServletResponse response) {
        
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonresult = new JsonObject();
            jsonresult.addProperty("result", request.getAttribute("isRegistered").toString());
            out.println(gson.toJson(jsonresult));
        } catch (Exception ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
