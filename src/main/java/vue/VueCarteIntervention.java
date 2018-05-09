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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Animal;
import modele.Intervention;
import modele.Livraison;
import fr.insalyon.dasi.proactifb3328.util.GeoTest;

/**
 *
 * @author Emilie Borghino
 */
public class VueCarteIntervention {

    public void carteIntervention(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            List<Intervention> list = (List<Intervention>) request.getAttribute("interventions");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
            JsonArray jsonList = new JsonArray();
            for(Intervention i : list)
            {
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
                jsonIntervention.addProperty("nintervention", i.getnIntervention());
                jsonIntervention.addProperty("EmployeNom", i.getEmploye().getNom());
                jsonIntervention.addProperty("EmployePrenom", i.getEmploye().getPrenom());
                jsonIntervention.addProperty("ClientPrenom", i.getClient().getPrenom());
                jsonIntervention.addProperty("ClientNom", i.getClient().getNom());
                jsonIntervention.addProperty("adresse", i.getClient().getAdresse());
                jsonIntervention.addProperty("LatLong", GeoTest.getLatLng(i.getClient().getAdresse()).toString());
                jsonIntervention.addProperty("description", i.getDescription());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = formatter.format(i.getDateDebut());
                jsonIntervention.addProperty("dateDebut", formattedDate);
                formatter = new SimpleDateFormat("HH:mm");
                String formattedHour = formatter.format(i.getDateDebut());
                jsonIntervention.addProperty("heureDebut", formattedHour);
                if(i.getDateFin() == null){
                    jsonIntervention.addProperty("dateFin","null");
                    jsonIntervention.addProperty("heureFin", "null");
                }else{
                    formatter = new SimpleDateFormat("dd/MM/yyyy");
                    formattedDate = formatter.format(i.getDateFin());
                    jsonIntervention.addProperty("dateFin", formattedDate);
                    formatter = new SimpleDateFormat("HH:mm");
                    formattedHour = formatter.format(i.getDateFin());
                    jsonIntervention.addProperty("heureFin", formattedHour);
                }
                jsonIntervention.addProperty("probleme", i.isProbleme());
                jsonIntervention.addProperty("commentaireFin", i.getCommentaireFin());
                jsonList.add(jsonIntervention);
            }
            JsonObject container = new JsonObject();
            container.add("list", jsonList);
            container.addProperty("pbConnexion", "false");
            out.println(gson.toJson(container));
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
    
    }
    
}
