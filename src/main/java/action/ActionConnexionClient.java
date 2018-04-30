package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import services.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eborghino
 */
public class ActionConnexionClient {
    public void  execute(HttpServletRequest request){
        HttpSession session = request.getSession();
        Client c = Service.connexionClient(Integer.parseInt(request.getParameter("login")));
        if(c != null){ 
            session.setAttribute("client", c);
            request.setAttribute("isConnected", 1); //true
        }
        else
             request.setAttribute("isConnected", 0); //false
        System.out.println(request.getAttribute("isConnected"));
    }
}
