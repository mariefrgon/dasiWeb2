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
public class VueInformationsClient {
    public void informationsClient(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Client c = (Client) session.getAttribute("client");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonPersonne = new JsonObject();
            jsonPersonne.addProperty("nClient", c.getnClient());
            jsonPersonne.addProperty("civilite", c.getCivilite());
            jsonPersonne.addProperty("nom", c.getNom());
            jsonPersonne.addProperty("prenom", c.getPrenom());
            jsonPersonne.addProperty("dateDeNaissance", c.getDateDeNaissance().toString());
            jsonPersonne.addProperty("adresse", c.getAdresse());
            jsonPersonne.addProperty("telephone", c.getTelephone());
            jsonPersonne.addProperty("mail", c.getMail());
            JsonArray jsonHistorique = new JsonArray();
            for(Intervention i : Service.recupererHistoriqueIntervention(c))
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
                jsonIntervention.addProperty("description", i.getDescription());
                jsonIntervention.addProperty("dateDebut", i.getDateDebut().toString());
                jsonIntervention.addProperty("dateFin", (i.getDateFin() == null ? "null" : i.getDateFin().toString()));
                jsonIntervention.addProperty("probleme", i.isProbleme());
                jsonIntervention.addProperty("commentaireFin", i.getCommentaireFin());
                jsonHistorique.add(jsonIntervention);
            }

            JsonObject container = new JsonObject();
            container.add("client", jsonPersonne);
            container.add("historique", jsonHistorique);
            out.println(gson.toJson(container));
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
