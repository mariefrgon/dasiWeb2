package action;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import vue.VueValidationDemande;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.proactifb3328.dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Client;
import services.Service;
import javax.servlet.http.HttpSession;
import modele.Animal;
import modele.Intervention;
import modele.Livraison;
import vue.VueInformationsClient;
import vue.VueValidationConnexion;
import vue.VueValidationInscription;
import vue.VueValidationDemande;
import vue.VueProblemeConnexion;
import vue.VueInformationEmploye;

/**
 *
 * @author eborghino
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if(action.equals("connecter")){
            ActionConnexionClient ACC = new ActionConnexionClient();
            ACC.execute(request);
            VueValidationConnexion VVC = new VueValidationConnexion();
            VVC.validationConnexion(request, response);
        }else if(action.equals("inscriptionClient")){
            ActionInscriptionClient AIC = new ActionInscriptionClient();
            AIC.execute(request);
            VueValidationInscription VVI = new VueValidationInscription();
            VVI.validationInscription(request, response);
        }else if(action.equals("connecterEmploye")){
            ActionConnexionEmploye ACE = new ActionConnexionEmploye();
            ACE.execute(request);
            VueValidationConnexion VVC = new VueValidationConnexion();
            VVC.validationConnexion(request, response);
        }else{
            if(session.getAttribute("client") == null && session.getAttribute("employe") == null){
                System.out.println("VOUS NETES PAS CONNECTE");
                VueProblemeConnexion VPC = new VueProblemeConnexion();
                VPC.problemeConnexion(request, response);
            }else if(action.equals("informationsEmploye")){
                ActionInformationEmploye AIE = new ActionInformationEmploye();
                AIE.execute(request);
                VueInformationEmploye VIE = new VueInformationEmploye();
                VIE.informationEmploye(request, response);
            }else if(action.equals("informationsClient") || action.equals("historiqueClient")){
                System.out.println(session.getAttribute("client"));
                VueInformationsClient VIC = new VueInformationsClient();
                VIC.informationsClient(request, response);
            }else if(action.equals("demandeAnimal")){
                ActionDemandeAnimal ADA = new ActionDemandeAnimal();
                ADA.execute(request);
                VueValidationDemande VVD = new VueValidationDemande();
                VVD.validationDemande(request, response);
            }else if(action.equals("demandeIncident")){
                ActionDemandeIncident ADI = new ActionDemandeIncident();
                ADI.execute(request);
                VueValidationDemande VVD = new VueValidationDemande();
                VVD.validationDemande(request, response);
            }else if(action.equals("demandeLivraison")){
                ActionDemandeLivraison ADI = new ActionDemandeLivraison();
                ADI.execute(request);
                VueValidationDemande VVD = new VueValidationDemande();
                VVD.validationDemande(request, response);
            }
        }
              
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.destroy();
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
