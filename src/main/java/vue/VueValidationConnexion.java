/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import action.ActionServlet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eborghino
 */
public class VueValidationConnexion {
     
    public void validationConnexion(HttpServletRequest request, HttpServletResponse response) {
        try{
            JsonObject jsonresult = new JsonObject();
            jsonresult.addProperty("result", request.getAttribute("isConnected").toString());
            jsonresult.addProperty("pbConnexion", "false");
            response.getWriter().println((jsonresult));
            response.getWriter().close();
        } catch (Exception ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
