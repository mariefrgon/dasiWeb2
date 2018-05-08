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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
            List<Intervention> list = Service.recupererHistoriqueIntervention(c);

            Collections.sort(list, new Comparator<Intervention>() {
                @Override
                public int compare(Intervention i1, Intervention i2) {
                    return i1.getDateDebut().compareTo(i2.getDateDebut()) * -1;
                };
            });
            
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
                    formattedDate = formatter.format(i.getDateFin());
                    jsonIntervention.addProperty("dateFin", formattedDate);
                    formattedHour = formatter.format(i.getDateFin());
                    jsonIntervention.addProperty("heureFin", formattedHour);
                }
                jsonIntervention.addProperty("probleme", i.isProbleme());
                jsonIntervention.addProperty("commentaireFin", i.getCommentaireFin());
                jsonHistorique.add(jsonIntervention);
            }

            JsonObject container = new JsonObject();
            container.add("client", jsonPersonne);
            container.add("historique", jsonHistorique);
            container.addProperty("pbConnexion", "false");
            out.println(gson.toJson(container));
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
