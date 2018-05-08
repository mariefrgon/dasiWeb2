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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Animal;
import modele.Employe;
import modele.Intervention;
import modele.Livraison;

/**
 *
 * @author Emilie Borghino
 */
public class VueInformationEmploye {

    public void informationEmploye(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Employe e = (Employe) session.getAttribute("employe");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonPersonne = new JsonObject();
            jsonPersonne.addProperty("prenom", e.getPrenom());
            Intervention i = (Intervention) request.getAttribute("intervention");
            JsonObject jsonIntervention = new JsonObject();
            jsonIntervention.addProperty("Type", i.getType());
            if(i.getType() == "Animal"){
                Animal a = (Animal) i;
                jsonIntervention.addProperty("animal", a.getAnimal());
            }
            if(i.getType() == "Livraison"){
                Livraison l = (Livraison) i;
                jsonIntervention.addProperty("objet", l.getObjet());
                jsonIntervention.addProperty("entreprise", l.getEntreprise());
            }
            jsonIntervention.addProperty("description", i.getDescription());
            jsonIntervention.addProperty("adresseClient", i.getClient().getAdresse());
            jsonIntervention.addProperty("prenomClient", i.getClient().getPrenom());
            jsonIntervention.addProperty("nomClient", i.getClient().getNom());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = formatter.format(i.getDateDebut());
            jsonIntervention.addProperty("dateDebut", formattedDate);
            formatter = new SimpleDateFormat("HH:mm");
            String formattedHour = formatter.format(i.getDateDebut());
            jsonIntervention.addProperty("heureDebut", formattedHour);

            JsonObject container = new JsonObject();
            container.add("employe", jsonPersonne);
            container.add("intervention", jsonIntervention);
            container.addProperty("pbConnexion", "false");
            out.println(gson.toJson(container));
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
}
